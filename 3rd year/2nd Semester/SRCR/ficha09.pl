% vim: syntax=prolog

%------------------------------------------------------------------------------
% SICStus PROLOG: Declaracoes iniciais ----------------------------------------
%------------------------------------------------------------------------------

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).
:- use_module(library(lists)).

%------------------------------------------------------------------------------
% Ficha 9 - Grafos ------------------------------------------------------------
%------------------------------------------------------------------------------


grafo([a, b, c, d, e, f, g],
      [
        aresta( a,b ),
        aresta( c,f ),
        aresta( c,d ),
        aresta( d,f ),
        aresta( f,g )
      ]).


%------------------------------------------------------------------------------
% 1. Escreva um predicado adjacente( X,Y,G ) que verifica se os nós X e Y são
% adjacentes no grafo G. ------------------------------------------------------
%------------------------------------------------------------------------------

adjacente( X,Y,grafo( _,Arestas ) ) :-
    (member( aresta( X,Y ),Arestas ));
    (member( aresta( Y,X ),Arestas )).

%------------------------------------------------------------------------------
% 2. Escreva um predicado caminho( G,A,B,P ) para encontrar um caminho acíclico
% P do nó A para o nó B no grafo G. -------------------------------------------
%------------------------------------------------------------------------------

caminho( G,A,B,P ) :- caminho1( G,A,[B],P ).

caminho1( _,A,[A|P1],[A|P1] ).
caminho1( G,A,[Y|P1],P ) :-
    adjacente( X,Y,G ),
    \+ member( X,[Y|P1] ),
    caminho1( G,A,[X,Y|P1],P ).

%------------------------------------------------------------------------------
% 3. Escreva um predicado ciclo( G,A,P ) para encontrar um caminho fechado P,
% que começa e acabe no nó A, no grafo G. -------------------------------------
%------------------------------------------------------------------------------

ciclo( G,A,P ) :-
    adjacente( B,A,G ),
    caminho( G,A,B,P1 ),
    append( P1,[A],P ).
