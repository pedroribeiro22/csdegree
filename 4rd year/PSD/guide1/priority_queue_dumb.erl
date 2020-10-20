-module(priority_queue_dumb).
-export([create/0, enqueue/3, dequeue/1]).

create() -> [].

enqueue([], Elem, Priority) -> [{Elem, Priority}];
enqueue([{Item, Level}|T], Elem, Priority) ->
    case Level < Priority of
        false -> enqueue(T, Elem, Priority);
        true -> [{Elem, Priority}] ++ [{Item, Level}|T]
    end.

dequeue([]) -> empty;
dequeue([H|T]) -> {H, T}.
