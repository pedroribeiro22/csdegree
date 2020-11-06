-module(room_manager).

% Suported operations
%   * Create Room
%   * Delete Room
%   * Enter Room
%   * Leave Room
%   * Submit message

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
      UpdatedMembers = Pids -- [Pid],
      case UpdatedMembers of
          [] ->
              exit(self());
           _ ->
              room(UpdatedMembers)
      end
  end.

start(GeneralPid) ->
    RoomManagerPid = spawn(fun() -> loop(#{"general" => GeneralPid}) end),
    register(?MODULE, RoomManagerPid),
    RoomManagerPid.

loop(Rooms) ->

    receive
        {create, RoomName, From} ->
            RoomPid = spawn(fun() -> room([From]) end),
            UpdatedRooms = maps:put(RoomName, RoomPid, Rooms),
            loop(UpdatedRooms);
        {delete, RoomName, From} ->
            RoomPid = maps:get(RoomName, Rooms),
            exit(RoomPid),
            UpdatedRooms = maps:remove(RoomName, Rooms),
            loop(UpdatedRooms);
        {enter, RoomName, From} ->
            RoomPid = maps:get(RoomName, Rooms),
            RoomPid ! {enter, From},
            loop(Rooms);
        {leave, RoomName, From} ->
            RoomPid = maps:get(RoomName, Rooms),
            RoomPid ! {leave, From},
            loop(Rooms)
    end.