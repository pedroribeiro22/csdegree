-module(priority_queue).
-export([my_create/0, my_enqueue/3, my_dequeue/1, tree_create/0, tree_enqueue/3, tree_dequeue/1]).
-import(myqueue, [create/1, enqueue/2, dequeue/1]).



my_create() -> {[], [], []}.

my_enqueue({Priority_1, Priority_2, Priority_3}, Elem, 1) -> {enqueue(Priority_1, Elem), Priority_2, Priority_3};
my_enqueue({Priority_1, Priority_2, Priority_3}, Elem, 2) -> {Priority_1, enqueue(Priority_2, Elem), Priority_3};
my_enqueue({Priority_1, Priority_2, Priority_3}, Elem, 3) -> {Priority_1, Priority_2, enqueue(Priority_3, Elem)}.

my_dequeue({[], [], []}) -> empty;
my_dequeue({[], Priority_2, Priority_3}) ->
    {Remainder, Element} = dequeue(Priority_2),
    {{[], Remainder, Priority_3}, Element};
my_dequeue({[], [], Priority_3}) ->
    {Remainder, Element} = dequeue(Priority_3),
    {{[], [], Remainder}, Element};
my_dequeue({Priority_1, Priority_2, Priority_3}) ->
    {Remainder, Element} = dequeue(Priority_1),
    {{Remainder, Priority_2, Priority_3}, Element}.



tree_create() -> gb_trees:empty().

tree_enqueue(Tree, Priority, Elem) ->
    case gb_trees:is_defined(Priority, Tree) of
        true -> 
            Elements = gb_trees:get(Priority, Tree),
            Updated = myqueue:enqueue(Elements, Elem),
            gb_trees:update(Priority, Updated, Tree);
        false ->
            gb_trees:enter(Priority, [Elem], Tree)
        
        end.

% tree_dequeue(Tree) ->
%     case myqueue:dequeue(gb_trees:get(1, Tree)) of
%         empty ->
%             case myqueue:dequeue(gb_trees:get(2, Tree)) of
%                 empty ->
%                     case myqueue:dequeue(gb_trees:get(3, Tree)) of
%                         empty -> empty;
%                         {Remainder, Element} ->
%                             NewTree = gb_trees:update(3, Remainder, Tree),
%                             {NewTree, Element}
%                         end;
%                 {Remainder2, Element2} ->
%                     NewTree2 = gb_trees:update(2, Remainder2, Tree),
%                     {NewTree2, Element2}
%                 end;
%         {Remainder3, Element3} ->
%             NewTree3 = gb_trees:update(1, Remainder3, Tree),
%             {NewTree3, Element3}
%     end.    

tree_dequeue(Tree) ->
    Keys = gb_trees:keys(Tree),
    tree_dequeue_helper(Keys, Tree).

tree_dequeue_helper([], _) -> empty;
tree_dequeue_helper([Key|Keys], Tree) ->
    case gb_trees:get(Key, Tree) of 
        [] ->
            tree_dequeue_helper(Keys, Tree);
        [Element|Remainder] ->
            NewTree = gb_trees:update(Key, Remainder, Tree),
            {NewTree, Element}
        end.