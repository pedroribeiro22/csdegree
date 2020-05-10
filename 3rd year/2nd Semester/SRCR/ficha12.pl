% vim: syntax=prolog

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% a) Problema de multiplos estados

move(s, a, 2).
move(a, b, 2).
move(b, c, 2).
move(c, d, 3).
move(d, t, 3).
move(s, e, 2).
move(e, f, 5).
move(f, g, 2).
move(g, t, 2).

estima(a, 5).
estima(b, 4).
estima(c, 4).
estima(d, 3).
estima(e, 7).
estima(f, 4).
estima(g, 2).
estima(s, 100).
estima(t, 0).

goal(t).

resolve_pp(Nodo,[Nodo|Caminho]):-
    profundidadefirst(Nodo,Caminho).

profundidadefirst(Nodo,[]):-
    goal(Nodo).

profundidadefirst(Nodo,[ProxNodo|Caminho]):-
    move(Nodo,ProxNodo,Custo),
    profundidadefirst(ProxNodo,Caminho).

dfCamsN(Nodo,L):-findall((S,C),(resolve_pp(s,S),length(S,C)),L).

dfCams(Nodo,L):-findall((S),(resolve_pp(s,S)),L).

dfTodasSolucoes(Nodo,L):- findall((S,C),(resolvedf(Nodo,S,C)),L).

resolvedf(Nodo, [Nodo|Caminho], Custo):-
    df(Nodo,Caminho,Custo).

df(Nodo,[],0):-
    goal(Nodo),!.

df(Nodo, [ProxNodo|Caminho], Custo):-
    move(Nodo,ProxNodo, Custo1),
    df(ProxNodo,Caminho, Custo2),
    Custo is Custo1 + Custo2.