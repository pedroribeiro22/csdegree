-module(efficient_myqueue).
-export([create/0, enqueue/2, dequeue/1]).
-import(lists, [reverse/1]).

create() -> {[], []}.

enqueue({Incoming, Outgoing}, Element) -> {[Element|Incoming], Outgoing}.

dequeue({_, []}) -> empty;
dequeue({Incoming, [H|T]}) -> {H, {Incoming, T}};
dequeue({Incoming, []}) -> dequeue({[], reverse(Incoming)}).
