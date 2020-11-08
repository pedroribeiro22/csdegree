-module(chat).
-export([start_server/1]).

-import(login_manager, [start/0, login/2, logout/1]).
-import(room_manager, [start_room_manager/0]).
-import(login_screen, [login_screen/4]).
-import(user, [loop/3]).

start_server(Port) ->
  {ok, ListenningSocket} = gen_tcp:listen(Port, [binary, {active, true}, {packet, line}, {reuseaddr, true}]),
  {RoomManagerPid, GeneralRoomPid} = room_manager:start(), 
  LogInManagerPid = login_manager:start(),
  spawn(fun() -> connection_establisher(ListenningSocket, RoomManagerPid, LogInManagerPid, GeneralRoomPid) end),
  ok.

connection_establisher(ListenningSocket, RoomManagerPid, LogInManagerPid, GeneralRoomPid) ->
  {ok, Socket} = gen_tcp:accept(ListenningSocket),
  spawn(fun() -> connection_establisher(ListenningSocket, RoomManagerPid, LogInManagerPid, GeneralRoomPid) end),
  login_screen:login_screen(Socket, LogInManagerPid, RoomManagerPid, GeneralRoomPid),
  RoomManagerPid ! {default_room, self()},
  receive
    {ok, DefaultRoomPid} ->
      DefaultRoomPid ! {enter, self()},
      user:loop(Socket, DefaultRoomPid, RoomManagerPid);
    _ ->
      exit(self())
  end.