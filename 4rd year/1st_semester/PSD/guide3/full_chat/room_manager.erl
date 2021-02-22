-module(room_manager).
-export([start/0]).

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

start() ->
    GeneralRoomPid = spawn(fun() -> room([]) end),
    RoomManagerPid = spawn(fun() -> loop(#{"general" => GeneralRoomPid}) end),
    {RoomManagerPid, GeneralRoomPid}.

create_room_helper(Rooms, RoomName, From) ->
  case maps:find(RoomName, Rooms) of
    {ok, _} ->
      From ! {invalid, <<"There is already one room registered with the provided name\n">>},
      loop(Rooms);
    _ ->
      NewRoomPid = spawn(fun() -> room([From]) end),
      UpdatedRooms = maps:put(RoomName, NewRoomPid, Rooms),
      io:format("\"~s\" room was created~n", [RoomName]),
      From ! {ok, <<"The room was created\nWe went ahead and signed you in it\n">>, NewRoomPid},
      loop(UpdatedRooms)
  end.

delete_room_helper(Rooms, RoomName, From) ->
  case maps:find(RoomName, Rooms) of
    {ok, RoomPid} ->
      UpdatedRooms = maps:remove(RoomName, Rooms),
      io:format("\"~s\" room was deleted~n", [RoomName]),
      exit(RoomPid),
      From ! {ok, <<"The room was successfuly deleted\n">>},
      loop(UpdatedRooms);
    _ ->
      From ! {invalid, <<"The room you tried to deleted does not exist\n">>},
      loop(Rooms)
  end.

enter_room_helper(Rooms, RoomName, From) ->
  case maps:find(RoomName, Rooms) of
    {ok, RoomPid} ->
      RoomPid ! {enter, From},
      From ! {ok, <<"You entered the workspace\n">>, RoomPid};
    _ ->
      From ! {invalid, <<"That room does not exist\n">>}
  end,
  loop(Rooms).

leave_room_helper(Rooms, RoomName, From) ->
  RoomPid = maps:get(RoomName, Rooms),
  RoomPid ! {leave, From},
  loop(Rooms).

message_room_helper(Rooms, RoomName, Data) ->
  RoomPid = maps:get(RoomName, Rooms),
  RoomPid ! {line, Data},
  loop(Rooms).

default_room_helper(Rooms, From) ->
  From ! {ok, maps:get("general", Rooms)},
  loop(Rooms).

loop(Rooms) ->

    receive

        {create, RoomName, From} ->
          create_room_helper(Rooms, RoomName, From);
        {delete, RoomName, From} ->
          delete_room_helper(Rooms, RoomName, From);
        {enter, RoomName, From} ->
          enter_room_helper(Rooms, RoomName, From);
        {leave, RoomName, From} ->
          leave_room_helper(Rooms, RoomName, From);
        {message, RoomName, Data} ->
          message_room_helper(Rooms, RoomName, Data);
        {default_room, From} ->
          default_room_helper(Rooms, From)

    end.