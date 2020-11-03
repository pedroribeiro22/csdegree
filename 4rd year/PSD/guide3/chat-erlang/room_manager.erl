-module(room_manager).
-export([start/0]).

room(Pids) ->
  receive
    {enter, Pid} ->
      io:format("user entered~n", []);
    {line, Data} = Msg ->
      io:format("received ~p~n", [Data]),
      [Pid ! Msg || Pid <- Pids],
      room(Pids);
    {leave, Pid} ->
      io:format("user left~n", [])
  end.

start() ->
    register(?MODULE, spawn(fun() -> loop(#{}) end)).

loop(Rooms) ->
   receive
        {create, Room} ->
            RoomPid = spawn(fun() -> room([]) end),
            NewRooms = maps:put(Room, {RoomPid, []}),
            loop(NewRooms);
        {join, Room, Pid} ->
            {RoomPid, Atendees} = maps:get(Room, Rooms),
            NewRooms = maps:update(Room, {RoomPid, [Pid | Atendees]}),
            loop(NewRooms);
        {leave, Room, Pid} ->
            {RoomPid, Atendees} = maps:get(Room, Rooms),
            NewRooms = maps:update(Room, {RoomPid, Atendees - [Pid]}),
            loop(NewRooms)
    end.

% Falta dar handle de mensagens