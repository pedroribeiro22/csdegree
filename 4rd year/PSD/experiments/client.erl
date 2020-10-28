-module(client).
-export([
        start/0]).
-import(server, []).

start() ->
    register(?MODULE, spawn(fun() -> client_loop() end)).

ask_for_server_broadcast(Message) ->
    server ! {broadcast, Message, self()}.

connect_to_server() ->
    server ! {connect, self()}.

client_loop() ->
    connect_to_server(),
    receive
        {message, Message, From} ->
            io:fwrite(Message)
    end.
