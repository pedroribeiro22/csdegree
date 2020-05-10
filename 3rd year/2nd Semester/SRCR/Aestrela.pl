% vim: syntax=prolog

%---------------------------------pesquisa a estrela 

resolveaestrela(Nodo, Caminho/Custo) :-
    estima(Nodo, Estima),
    aestrela([[Nodo]/0/Estima], InvCaminho/Custo/),
    inverso(InvCaminho, Caminho).

aestrela(Caminhos, SolucaoCaminho) :-
    obtem_melhor(Caminhos, MelhorCaminho),
    seleciona(MelhorCaminho, Caminhos, OutrosCaminhos),
    expande_aestrela(MelhorCaminho, ExpCaminhos),
    append(OutrosCaminhos, ExpCaminhos, NovoCaminhos),
    aestrela(NovoCaminhos, SolucaoCaminho).


expande_aestrela(Caminho, ExpCaminhos) :-
    findall(NovoCaminho, move_aestrela(Caminho,NovoCaminho), ExpCaminhos).

moveaestrela([Nodo|Caminho]/Custo/, [ProxNodo,Nodo|Caminho]/NovoCusto/Est) :-
    move(Nodo, ProxNodo, PassoCusto),+ member(ProxNodo, Caminho),
    NovoCusto is Custo + PassoCusto,
    estima(ProxNodo, Est).


obtem_melhor([Caminho], Caminho) :- !.

obtemmelhor([Caminho1/Custo1/Est1,/Custo2/Est2|Caminhos], MelhorCaminho) :-
    Custo1 + Est1 =< Custo2 + Est2, !,
    obtem_melhor([Caminho1/Custo1/Est1|Caminhos], MelhorCaminho).

obtemmelhor([|Caminhos], MelhorCaminho) :- 
    obtem_melhor(Caminhos, MelhorCaminho).


aestrela(Caminhos, Caminho) :-
    obtemmelhor(Caminhos, Caminho),
    Caminho = [Nodo|]//,goal(Nodo).

15:55

%---------------------------------predicados auxiliares

inverso(Xs, Ys):-
    inverso(Xs, [], Ys).

inverso([], Xs, Xs).
inverso([X|Xs],Ys, Zs):-
    inverso(Xs, [X|Ys], Zs).

seleciona(E, [E|Xs], Xs).
seleciona(E, [X|Xs], [X|Ys]) :- seleciona(E, Xs, Ys).

minimo([(P,X)],(P,X)).
minimo([(Px,X)|L],(Py,Y)):- minimo(L,(Py,Y)), X>Y. 
minimo([(Px,X)|L],(Px,X)):- minimo(L,(Py,Y)), X=<Y.