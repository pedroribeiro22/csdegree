-module(login_screen).
-export([loop/4]).

-import(user, [loop/3]).

login_helper(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid, Input) ->
  InputText = lists:droplast(binary_to_list(Input)),
  [Username, Password] = string:split(InputText, " "),
  LogInManagerPid ! {login, Username, Password, self()},
  receive
    {ok, _} ->
      io:format("~s logged in~n", [Username]),
      gen_tcp:send(Sock, <<"Login was successful\n">>),
      user:loop(Sock, DefaultRoomPid, RoomManagerPid);
    _ ->
      gen_tcp:send(Sock, <<"The credentials are wrong. Login was unsuccessful\n">>),
      loop(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
  end.

create_account_helper(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid, Input) ->
      InputText = lists:droplast(binary_to_list(Input)),
      [Username, Password] = string:tokens(InputText, " "),
      LogInManagerPid ! {create_account, Username, Password, self()},
      receive
        {ok, _} ->
          io:format("~s created an account~n", [Username]),
          gen_tcp:send(Sock, <<"The account was successfuly created\n">>),
          loop(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid);
        _ ->
          gen_tcp:send(Sock, <<"There's already an account with that username registered\n">>),
          loop(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end.

delete_account_helper(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid, Input) ->
      InputText = lists:drop(binary_to_list(Input)),
      [Username, Password] = string:tokens(InputText, " "),
      LogInManagerPid ! {close_account, Username, Password, self()},
      receive
        {ok, _} ->
          io:format("~s deleted the account~n", [Username]),
          gen_tcp:send(Sock, <<"The account was successfuly deleted\n">>),
          loop(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid);
        {invalid, _} ->
          gen_tcp:send(Sock, <<"Either the account does not exist or the credentials are wrong\n">>),
          loop(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid)
      end.

loop(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid) ->
  receive
    {tcp, _, <<"\\login ", Input/binary>> } ->
      login_helper(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid, Input);
    {tcp, _, <<"\\create_account ", Input/binary>> } ->
      create_account_helper(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid, Input);
    {tcp, _, <<"\\delete_account" , Input/binary>> } ->
      delete_account_helper(Sock, LogInManagerPid, RoomManagerPid, DefaultRoomPid, Input)
    end.
