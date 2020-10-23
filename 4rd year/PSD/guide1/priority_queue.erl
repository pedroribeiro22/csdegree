-module(priority_queue).
-export([my_create/0, my_enqueue/3, my_dequeue/1, trees_my_create/2, trees_my_enqueue/3, trees_my_dequeue/2]).
-import(myqueue, [create/1, enqueue/2, dequeue/1]).
-import(gb_trees, [tree/2, get/2, update/3]).


% É mais fácil assumir que o número de prioridades era conhecido à priori
% Vamos assumir neste caso que o número de prioridades é 3

% --- a) ---

my_create() -> {[], [], []}.

my_enqueue({Priority_1, Priority_2, Priority_3}, Elem, 1) -> {myqueue:enqueue(Priority_1, Elem), Priority_2, Priority_3};
my_enqueue({Priority_1, Priority_2, Priority_3}, Elem, 2) -> {Priority_1, myqueue:enqueue(Priority_2, Elem), Priority_3};
my_enqueue({Priority_1, Priority_2, Priority_3}, Elem, 3) -> {Priority_1, Priority_2, myqueue:enqueue(Priority_3, Elem)}.

my_dequeue({[], [], []}) -> empty;
my_dequeue({[], Priority_2, Priority_3}) ->
    {Remainder, Element} = myqueue:dequeue(Priority_2),
    {{[], Remainder, Priority_3}, Element};
my_dequeue({[], [], Priority_3}) ->
    {Remainder, Element} = myqueue:dequeue(Priority_3),
    {{[], [], Remainder}, Element};
my_dequeue({Priority_1, Priority_2, Priority_3}) ->
    {Remainder, Element} = myqueue:dequeue(Priority_1),
    {{Remainder, Priority_2, Priority_3}, Element}.


% --- b) ---

% my_create() -> // create new tree;

trees_my_create(Priority, Elem) -> tree(Priority, [Elem]).

trees_my_enqueue(Tree, Priority, Elem) ->
    PriorityQueue = gb_trees:get(Priority, Tree),
    NewQueue = myqueue:enqueue(PriorityQueue, Elem),
    gb_trees:update(Priority, NewQueue, Tree).

% Returns: {Element, UpdatedTree}
trees_my_dequeue(Tree, Priority) -> 
    PriorityQueue = gb_trees:get(Priority, Tree),
    {Remainder, Element} = myqueue:dequeue(PriorityQueue),
    {Element, gb_trees:update(Priority, Remainder, Tree)}.