%------------------------------------------------
%SICStusPROLOG:Declaracoesiniciais
%------------------------------------------------

:-set_prolog_flag(discontiguous_warnings,off).

:-set_prolog_flag(single_var_warnings,off).

:-set_prolog_flag(unknown,fail).

%------------------------------------------------
%SICStusPROLOG:definicoesiniciais
%------------------------------------------------

:-op(900,xfy,'::').

:-dynamic pai/2.
:-dynamic mae/2.
:-dynamic nascimento/2.

%------------------------------------------------
% UTILITIES
%------------------------------------------------

solucoes( X,Y,Z ) :-
    findall( X,Y,Z ).


comprimento( S,N ) :-
    length( S,N ).


evolucao( Termo ) :-
    solucoes( Invariante,+Termo::Invariante,Lista ),
    insercao( Termo ),
    teste( Lista ).


insercao( Termo ) :-
    assert( Termo ).
insercao( Termo ) :-
    retract( Termo ),
    !,
    fail.


teste( [] ).
teste( [R|LR] ) :-
    R,
    teste( LR ).

%------------------------------------------------
% Testa se uma proposição consta da BC
%------------------------------------------------

nao( Q ) :-
    Q, !, fail;
    true.

%------------------------------------------------
% Sistema de inferência
%------------------------------------------------

demo( Q,verdadeiro ) :-
    Q,
    nao(excecao(Q)),
    nao(nao(Q)).
demo( Q,falsa ) :-
    nao(Q).
    nao( excecao( Q ) ).
demo( Q,desconhecido ) :-
    excecao( Q ).

%------------------------------------------------
% INVARIANTES 
%------------------------------------------------

+pai( Pai,Filho) :: (solucoes( (Filho),(pai(P, Filho)), S),
                    comprimento( S,N ),
                    N =< 1).

+mae( Mae,Filho) :: (solucoes( (Filho),(mae(M, Filho)), S),
                    comprimento( S,N ),
                    N =< 1).

+nascimento( Pessoa,Data ) :: (solucoes( (Pessoa), (nascimento( Pessoa,D )), S),
                               comprimento( S,N ),
                               N =< 1).

%------------------------------------------------
% QUESTION 1
%------------------------------------------------

pai( Abel,Ana ).
mae( Alice,Ana ).
nascimento( Ana,1/1/2010 ).

%------------------------------------------------
% QUESTION 2
%------------------------------------------------

pai( Antonio,Anibal ).
mae( Alberta,Anibal ).
nascimento( Anibal,2/1/2010 ).

%------------------------------------------------
% QUESTION 3
%------------------------------------------------

pai( Bras,Berta ).
mae( Belem,Berta ).
nascimento( Berta,2/2/2010 ).

pai( Bras,Berto ).
mae( Belem,Berto ).
nascimento( Berto,2/2/2010 ).

%------------------------------------------------
% QUESTION 4
%------------------------------------------------

nascimento( Catia,3/3/2010 ).

%------------------------------------------------
% QUESTION 5
% VALOR NULO IMPRECISO
%------------------------------------------------

mae( Catia,Crispim ).
excecao( pai( Celso,Crispim )).
excecao( pai( Caio,Crispim )).

%------------------------------------------------
% QUESTION 6
% VALOR NULO INCERTO
%------------------------------------------------

%pai( Daniel,Danilo ).
%mae( nulo_incerto_mae,Danilo ).
%nuloIncerto(nulo_incerto_mae).
%excecao( mae( M,F ) ) :-
%    mae( nulo_incerto_mae, F).
%nascimento( Danilo,4/4/2010 ).

%------------------------------------------------
% QUESTION 7
% VALOR NULO IMPRECISO
%------------------------------------------------

pai( Elias,Eurico ).
mae( Elsa,Eurico ).
excecao( nascimento( Eurico,5/5/2010 )).
excecao( nascimento( Eurico,15/5/2010 )).
excecao( nascimento( Eurico,25/5/2010 )).

%------------------------------------------------
% QUESTION 8
% VALOR NULO INCERTO
%------------------------------------------------

%excecao( pai( Fausto,Fabia ) ).
%excecao( pai( Fausto,Octavia ) ).

%mae( nulo_incerto_mae,Fabia ).
%mae( nulo_incerto_mae,Octavia ).
%nuloIncerto(nulo_incerto_mae).
%excecao( nulo_incerto_mae,Fabia ).
%excecao( nulo_incerto_mae,Octavia ).
%excecao( mae( M,F ) ) :-
%    mae( nulo_incerto_mae,F ).

%------------------------------------------------
% QUESTION 9 
%------------------------------------------------

%pai( Guido,Golias ).
%mae( Guida,Golias ).
%nascimento( Golias,nulo_interdito_nascimento ).
%nuloInterdito( nulo_interdito_nascimento ).
%nao( nascimento( Pessoa,Data ) ) :-
%    nascimento( Pessoa, nulo_interdito_nascimento ).

%------------------------------------------------
% QUESTION 10 
%------------------------------------------------

%excecao( nascimento( Helder,Data ) ) :-
%    Data \= 8/8/2010.

%------------------------------------------------
% QUESTION 11 
%------------------------------------------------

%excecao( nascimento( Ivo,Data ) ) :-
%    Data = 1/6/2010;
%    Data = 2/6/2010;
%    Data = 3/6/2010;
%    Data = 4/6/2010;
%    Data = 5/6/2010;
%    Data = 6/6/2010;
%    Data = 7/6/2010;
%    Data = 8/6/2010;
%    Data = 9/6/2010;
%    Data = 10/6/2010;
%    Data = 11/6/2010;
%    Data = 12/6/2010;
%    Data = 13/6/2010;
%    Data = 14/6/2010.