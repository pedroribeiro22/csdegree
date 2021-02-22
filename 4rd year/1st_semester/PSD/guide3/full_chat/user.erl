-module(user).
-export([loop/3]).

loop(Sock, Room, RoomManagerPid) ->
  Self = self(),
  receive
    {line, {Self, Data}} ->
      inet:setopts(Sock, [{active, once}]),
      gen_tcp:send(Sock, Data),
      loop(Sock, Room, RoomManagerPid);
    {line, {_, Data}} ->
      gen_tcp:send(Sock, Data),
      loop(Sock, Room, RoomManagerPid);
    {tcp, _, <<"\\room ", RoomName/binary>> } ->
      Input = lists:droplast(binary_to_list(RoomName)),
      RoomManagerPid ! {enter, Input, self()},
      inet:setopts(Sock, [{active, once}]),
      receive
        {ok, Message, RoomPid} ->
          gen_tcp:send(Sock, Message),
          loop(Sock, RoomPid, RoomManagerPid);
        {invalid, Message} ->
          gen_tcp:send(Sock, Message),
          loop(Sock, Room, RoomManagerPid)
      end;
    {tcp, _, <<"\\create_room ", RoomName/binary>> } ->
      Input = lists:droplast(binary_to_list(RoomName)),
      RoomManagerPid ! {create, Input, self()},
      inet:setopts(Sock, [{active, once}]),
      receive
        {ok, Message, RoomPid} ->
          gen_tcp:send(Sock, Message),
          loop(Sock, RoomPid, RoomManagerPid);
        {invalid, Message} ->
          gen_tcp:send(Sock, Message),
          loop(Sock, Room, RoomManagerPid)
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
      loop(Sock, Room, RoomManagerPid);
    {tcp_closed, _} ->
      Room ! {leave, self()};
    {tcp_error, _, _} ->
      Room ! {leave, self()}
  end.