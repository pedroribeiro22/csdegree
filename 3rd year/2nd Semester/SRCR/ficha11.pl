% vim syntax=prolog


%------------------------------------------------------------------------------
% SICStus PROLOG: Declaracoes iniciais ----------------------------------------
%------------------------------------------------------------------------------

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).
:- use_module(library(lists)).

%------------------------------------------------------------------------------
% Pergunta a) -----------------------------------------------------------------
%------------------------------------------------------------------------------

% É um problema de estado único.


%------------------------------------------------------------------------------
% Pergunta b) -----------------------------------------------------------------
%------------------------------------------------------------------------------

% Estado Inicial --------------------------------------------------------------

inicial(0, 0).

% Estados finais --------------------------------------------------------------

final(4, _).
final(_, 4).

transicao(jarros(v1, v2), encher(2), jarros(v1, 5)) :- v2 < 5.

transicao(jarros(v1, v2), vazio(1), jarros(0, v2)) :- v1 > 0.
transicao(jarros(v1, v2), vazio(2), jarros(v1, 0)) :- v2 > 0.

transicao(jarros(v1, v2), transferir(1, 2), jarros(NV1, NV2)) :-
    v1 > 0,
    NV1 is max(V1 - 5 + V2, 0),
    NV1 < V1,
    NV2 is V2 + V1 - NV1.

transicao(jarros(v1, v2), transferir(2, 1), jarros(NV1, NV2)) :-
    v2 > 0,
    NV2 is max(V2 - 8 + V1, 0),
    NV2 < V2,
    NV1 is V1 + V2 - NV2.


resolvedf(Solucao) :-
    inicial(EstadoInicial),
    resolvedf(InicialEstado, [EstadoInicial], Solucao).

resolvedf(Estado, _, []) :-
    final(Estado), !.

resolvedf(InicialEstado, Historico, [Move|Solucao]) :-
    transicao(Estado, Move, Estado1),
    nao(pertence(Estado1, Historico)),
    resolvedf(Estado1, [Estado1|Historico], Solucao).

todasSolucoes(L) :-
    findall((S, C), (resolvedf(S), length(S, C)), L).
