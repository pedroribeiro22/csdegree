-module(server).
-export([
    start/0]).

start() ->
    register(?MODULE, spawn(fun() -> server_loop([]) end)).

send_message_to_clients(Message, [Client]) ->
    Client ! {message, Message, Client};
send_message_to_clients(Message, [Client|Clients])  ->
    Client ! {message, Message, ?MODULE},
    send_message_to_clients(Message, Clients).

server_loop(Clients) ->
    receive
        {connect, From} ->
            UpdatedClients = lists:append(Clients, [From]),
            From ! {success, ?MODULE},
            server_loop(UpdatedClients);
        {broadcast, Message, _} ->
            send_message_to_clients(Message, Clients),
            server_loop(Clients)
    end.
