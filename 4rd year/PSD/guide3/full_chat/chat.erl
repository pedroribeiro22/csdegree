-module(chat).
-import(login_manager, [start/0, login/2, logout/1]).
-export([start_server/1]).

start_server(Port) ->
  {ok, LSock} = gen_tcp:listen(Port, [binary, {active, once}, {packet, line},
                                      {reuseaddr, true}]),
  RoomManagerPid = start_room_manager(), 
  LogInManagerPid = login_manager:start(),
  spawn(fun() -> acceptor(LSock, RoomManagerPid, LogInManagerPid) end),
  ok.

start_room_manager() ->
    GeneralRoomPid = spawn(fun() -> room([]) end),
    RoomManagerPid = spawn(fun() -> loop_room_manager(#{"general" => GeneralRoomPid}) end),
    RoomManagerPid.

% A user is always accepted into the "general" channel

acceptor(LSock, RoomManagerPid, LogInManagerPid) ->
  {ok, Sock} = gen_tcp:accept(LSock),
  spawn(fun() -> acceptor(LSock, RoomManagerPid, LogInManagerPid) end),
  RoomManagerPid ! {default_room, self()},
  receive
    {ok, DefaultRoomPid} ->
      DefaultRoomPid ! {enter, self()},
      user(Sock, DefaultRoomPid, RoomManagerPid);
    _ ->
      exit(self())
  end.

login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid) ->
  receive
    {tcp, _, <<"\\login ", Username/binary, " ", Password/binary>> } ->
      UsernameStr = lists:droplast(binary_to_list(Username)),
      PasswordStr = lists:droplast(binary_to_list(Password)),
      LogInManagerPid ! {login, UsernameStr, PasswordStr, self()},
      receive
        {ok, _} ->
          gen_tcp:send(Sock, <<"Login was sucessful\n">>),
          user(Sock, DefaultRoomPid, RoomManagerPid);
        _ ->
          gen_tcp:send(Sock, <<"The credentials are wrong. Login was unsuccessful\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end;
    {tcp, _, <<"\\create_account ", Username/binary, " ", Password/binary>> } ->
      UsernameStr = lists:droplast(binary_to_list(Username)),
      PasswordStr = lists:droplast(binary_to_list(Password)),
      LogInManagerPid ! {create_account, UsernameStr, PasswordStr, self()},
      receive
        {ok, _} ->
          gen_tcp:send(Sock, <<"The account was successfuly created\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid);
        _ ->
          gen_tcp:send(Sock, <<"There's already an account with that username registered\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end;
    {tcp, _, <<"\\delete_account" , Username/binary, " ", Password/binary>> } ->
      UsernameStr = lists:droplast(binary_to_list(Username)),
      PasswordStr = lists:droplast(binary_to_list(Password)),
      LogInManagerPid ! {close_account, UsernameStr, PasswordStr, self()},
      receive
        {ok, _} ->
          gen_tcp:send(Sock, <<"The account was successfuly deleted\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid);
        {invalid, _} ->
          gen_tcp:send(Sock, <<"Either the account does not exist or the credentials are wrong\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end
    end.



user(Sock, Room, RoomManagerPid) ->
  Self = self(),
  receive
    {line, {Self, Data}} ->
      inet:setopts(Sock, [{active, once}]),
      gen_tcp:send(Sock, Data),
      user(Sock, Room, RoomManagerPid);
    {line, {_, Data}} ->
      gen_tcp:send(Sock, Data),
      user(Sock, Room, RoomManagerPid);
    {tcp, _, <<"\\room ", RoomName/binary>> } ->
      Input = lists:droplast(binary_to_list(RoomName)),
      RoomManagerPid ! {enter, Input, self()},
      inet:setopts(Sock, [{active, once}]),
      receive
        {ok, Message, RoomPid} ->
          gen_tcp:send(Sock, Message),
          user(Sock, RoomPid, RoomManagerPid);
        {invalid, Message} ->
          gen_tcp:send(Sock, Message),
          user(Sock, Room, RoomManagerPid)
      end;
    {tcp, _, <<"\\create_room ", RoomName/binary>> } ->
      Input = lists:droplast(binary_to_list(RoomName)),
      RoomManagerPid ! {create, Input, self()},
      inet:setopts(Sock, [{active, once}]),
      receive
        {ok, Message, RoomPid} ->
          gen_tcp:send(Sock, Message),
          user(Sock, RoomPid, RoomManagerPid);
        {invalid, Message} ->
          gen_tcp:send(Sock, Message),
          user(Sock, Room, RoomManagerPid)
      end;
    {tcp, _, <<"\\delete_room ", RoomName/binary>> } ->
      Input = lists:droplast(binary_to_list(RoomName)),
      RoomManagerPid ! {delete, Input, self()},
      inet:setopts(Sock, [{active, once}]),
      receive
        {ok, Message} ->
          gen_tcp:send(Sock, Message);
        {invalid, Message} ->
          gen_tcp:send(Sock, Message)
      end;
    {tcp, _, Data} ->
      Room ! {line, {Self, Data}},
      user(Sock, Room, RoomManagerPid);
    {tcp_closed, _} ->
      Room ! {leave, self()};
    {tcp_error, _, _} ->
      Room ! {leave, self()}
  end.



loop_room_manager(Rooms) ->
    receive
        {create, RoomName, From} ->
          case maps:find(RoomName, Rooms) of
            {ok, _} ->
              From ! {invalid, <<"There is already one room registered with the provided name\n">>},
              loop_room_manager(Rooms);
            _ ->
              NewRoomPid = spawn(fun() -> room([From]) end),
              UpdatedRooms = maps:put(RoomName, NewRoomPid, Rooms),
              From ! {ok, <<"The room was created\nWe went ahead and signed you in it\n">>, NewRoomPid},
              loop_room_manager(UpdatedRooms)
            end;
        {delete, RoomName, From} ->
            case maps:find(RoomName, Rooms) of
              {ok, RoomPid} ->
                UpdatedRooms = maps:remove(RoomName, Rooms),
                exit(RoomPid),
                From ! {ok, <<"The room was successfuly deleted\n">>},
                loop_room_manager(UpdatedRooms);
              _ ->
                From ! {invalid, <<"The room you tried to deleted does not exist\n">>},
                loop_room_manager(Rooms)
              end;
        {enter, RoomName, From} ->
            case maps:find(RoomName, Rooms) of
              {ok, RoomPid} ->
                RoomPid ! {enter, From},
                From ! {ok, <<"You entered the workspace\n">>, RoomPid};
              _ ->
                From ! {invalid, <<"That room does not exist\n">>}
              end,
            loop_room_manager(Rooms);
        {leave, RoomName, From} ->
            RoomPid = maps:get(RoomName, Rooms),
            RoomPid ! {leave, From},
            loop_room_manager(Rooms);
        {message, RoomName, Data} ->
            RoomPid = maps:get(RoomName, Rooms),
            RoomPid ! {line, Data},
            loop_room_manager(Rooms);
        {default_room, From} ->
            From ! {ok, maps:get("general", Rooms)},
            loop_room_manager(Rooms)
    end.

room(Pids) ->
  receive
    {enter, Pid} ->
      io:format("user entered~n", []),
      room([Pid | Pids]);
    {line, Data} = Msg ->
      io:format("received ~p~n", [Data]),
      [Pid ! Msg || Pid <- Pids],
      room(Pids);
    {leave, Pid} ->
      io:format("user left~n", []),
      room(Pids -- [Pid])
  end.


