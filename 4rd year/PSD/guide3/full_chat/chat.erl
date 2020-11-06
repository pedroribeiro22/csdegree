-module(chat).
-import(login_manager, [create_acoount/2, close_account/2, login/2, logout/1]).
-import(room_manager, [start/1]).
-import(user_manager, [up/0]).
-export([start_server/1]).

start_server(Port) ->
  {ok, LSock} = gen_tcp:listen(Port, [binary, {active, once}, {packet, line},
                                      {reuseaddr, true}]),
  UserManagerPid = spawn(fun() -> user_manager:up() end),
  GeneralRoomPid = spawn(fun() -> room_manager:room([]) end),
  RoomManagerPid = room_manager:start(GeneralRoomPid),
  spawn(fun() -> acceptor(LSock, UserManagerPid, GeneralRoomPid) end),
  ok.

acceptor(LSock, UserManagerPid, GeneralRoomPid) ->
  {ok, Sock} = gen_tcp:accept(LSock),
  spawn(fun() -> acceptor(LSock, UserManagerPid, GeneralRoomPid) end),
  login_register_panel(Sock, UserManagerPid, GeneralRoomPid).

login_register_panel(Sock, UserManagerPid, GeneralRoomPid) ->
  LoginRegisterPanelPid = self(),
  receive
    {tcp, {Self, Data}} ->
      inet:setopts(Sock, [{active, once}]),
      gen_tcp:send(Sock, Data),
      case string:tokens(Data, [$\s]) of
        [Keyword, Field1, Field2] ->
          case Keyword of
            "login" ->
              UserManagerPid ! {login, Field1, Field2, LoginRegisterPanelPid},
              receive
                {ok, _} ->
                  gen_tcp:send(Sock, <<"Login was successful!~n">>),
                  GeneralRoomPid ! {enter, LoginRegisterPanelPid},
                  user(Sock, GeneralRoomPid);
                _ ->
                  gen_tcp:send(Sock, <<"Login unsuccessful, please try again!~n">>),
                  login_register_panel(Sock, UserManagerPid, GeneralRoomPid)
              end;
            "register" ->
              UserManagerPid ! {register, Field1, Field2, LoginRegisterPanelPid},
              receive
                {ok, _} ->
                  gen_tcp:send(Sock, <<"Your account has been successfuly registered!~n">>),
                  login_register_panel(Sock, UserManagerPid, GeneralRoomPid);
                _ ->
                  gen_tcp:send(Sock, <<"The account could not be registered!~n">>),
                  login_register_panel(Sock, UserManagerPid, GeneralRoomPid)
              end;
            "unregister" ->
              UserManagerPid ! {delete, Field1, Field2, LoginRegisterPanelPid},
              receive
                {ok, _} ->
                  gen_tcp:send(Sock, <<"The account was successfuly removed!~n">>),
                  login_register_panel(Sock, UserManagerPid, GeneralRoomPid);
                _ ->
                  gen_tcp:send(Sock, <<"The account could not be removed!~n">>),
                  login_register_panel(Sock, UserManagerPid, GeneralRoomPid)
              end
            end;
        _ ->
          gen_tcp:send(Sock, <<"This operation is not supported at this stage!~n">>)
      end
  end.


user(Sock, Room) ->
  Self = self(),
  receive
    {line, {Self, Data}} ->
      inet:setopts(Sock, [{active, once}]),
      gen_tcp:send(Sock, Data),
      user(Sock, Room);
    {line, {_, Data}} ->
      gen_tcp:send(Sock, Data),
      user(Sock, Room);
    {tcp, _, Data} ->
      Room ! {line, {Self, Data}},
      user(Sock, Room);
    {tcp_closed, _} ->
      Room ! {leave, self()};
    {tcp_error, _, _} ->
      Room ! {leave, self()}
  end.

