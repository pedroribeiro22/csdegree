-module(login_screen).
-export([login_screen/4]).

-import(user, [loop/3]).


login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid) ->
  receive
    {tcp, _, <<"\\login ", Input/binary>> } ->
      InputText = lists:droplast(binary_to_list(Input)),
      [Username, Password] = string:split(InputText, " "),
      LogInManagerPid ! {login, Username, Password, self()},
      receive
        {ok, _} ->
          io:format("~s logged in~n", [Username]),
          gen_tcp:send(Sock, <<"Login was sucessful\n">>),
          user:loop(Sock, DefaultRoomPid, RoomManagerPid);
        _ ->
          gen_tcp:send(Sock, <<"The credentials are wrong. Login was unsuccessful\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end;
    {tcp, _, <<"\\create_account ", Input/binary>> } ->
      InputText = lists:droplast(binary_to_list(Input)),
      [Username, Password] = string:tokens(InputText, " "),
      LogInManagerPid ! {create_account, Username, Password, self()},
      receive
        {ok, _} ->
          io:format("~s created an account~n", [Username]),
          gen_tcp:send(Sock, <<"The account was successfuly created\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid);
        _ ->
          gen_tcp:send(Sock, <<"There's already an account with that username registered\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end;
    {tcp, _, <<"\\delete_account" , Input/binary>> } ->
      InputText = lists:drop(binary_to_list(Input)),
      [Username, Password] = string:tokens(InputText, " "),
      LogInManagerPid ! {close_account, Username, Password, self()},
      receive
        {ok, _} ->
          io:format("~s deleted the account~n", [Username]),
          gen_tcp:send(Sock, <<"The account was successfuly deleted\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid);
        {invalid, _} ->
          gen_tcp:send(Sock, <<"Either the account does not exist or the credentials are wrong\n">>),
          login_screen(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end
    end.
