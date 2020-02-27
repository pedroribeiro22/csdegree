%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3
% FICHA 02

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

% Resolução da FICHA 02

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Exercício i)

soma2(X, Y, Z) :- Z is X + Y.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Exercício ii)

soma3(X, Y, Z, W) :- W is X + Y + Z.

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Exercício iii)
soma_lista([], 0).
soma_lista([H|T], Z) :- soma_lista(T, R),
        Z is H + R.


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Exercício iv)
