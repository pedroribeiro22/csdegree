%------------------------------------------------

%Programacaoemlogicaestendida

%Representacaodeconhecimentoimperfeito



%------------------------------------------------

%SICStusPROLOG:Declaracoesiniciais



:-set_prolog_flag(discontiguous_warnings,off).

:-set_prolog_flag(single_var_warnings,off).

:-set_prolog_flag(unknown,fail).



%------------------------------------------------

%SICStusPROLOG:definicoesiniciais



:-op(900,xfy,'::').

:-dynamicjogo/3.





%--------------------------------------------questãoi

jogo(1,aa,500).



%--------------------------------------------questãoii

jogo(2,bb,x).

excecao( jogo ( Jogo,Arbitro,Ajudas ) ):-
    jogo( Jogo,Arbitro,x ).

%--------------------------------------------questãoiii

excecao( jogo( 3,cc,500 ) ).
excecao( jogo( 3,cc,2500 ) ).

%--------------------------------------------questãoiv

excecao ( jogo ( 4,cc,valor ) ) :-
    valor >= 250,
    valor <= 750.

%-------------------------------------------questãov

nulo(xpto).

excecao( jogo ( 5,ee,xpto )).

%-------------------------------------------questãovi

excecao( jogo ( 6,ff,250)).

excecao( jogo ( 7,ff,valor ) ) :-
    valor > 5000.

%-------------------------------------------questãovii

-jogo ( 7,gg,2500 ).

nulo( x7 ).

jogo ( 7,gg, x7).

%-------------------------------------------questão vii

cerca(X,Sup,Inf):-
    Sup is X*1.25,
    Inf is X*0.75.

excecao(8,,Ajudas) :-
    Ajudas>= Inf,
    Ajudas<=Sup,
    cerca(1000,Sup,Inf).

%-------------------------------------------questão ix

excecao(9,ii,Ajudas):-
    Ajudas>= Inf,
    Ajudas<=Sup,
    cerca(3000,Sup,Inf)

%-------------------------------------------questãox

+jogo( Id,Ref,Valor ) :: (solucoes( (Id),(jogo( Id, R , V )),S ),
                  comprimento( S,N ),
                  N =< 1  ).

%-------------------------------------------questão xi

+jogo(Id,Ref,Valor ) :: (solucoes( (Ref),(jogo( I, Ref , V )),S1 ),
                  solucoes( (Ref), excecao((jogo( I, Ref , V ))),S2 ),
                  comprimento( S1,N1 ),comprimento( S2,N2),
                  N+N1 =< 3 ).

%-------------------------------------------questão xii

+jogo(Id,Ref,Valor) :: (solucoes( (I),(jogo( I, Ref , V )),S1 ),
                  solucoes( (I), excecao((jogo( I, Ref , V ))),S2 ),
                  mergelist(S1,S2,lista),
                  bubblesort(lista,orden),
                  noconsecs(orden)).

mergelist([],[],[]).
mergelist([X],[],[X]).
mergelist([],[Y],[Y]).
mergelist(X,[],X).
mergelist([],Y,Y).
mergelist([X|List1],[Y|List2],[X|List]) :-
        X =< Y,!,
        mergelist(List1,[Y|List2],List).
mergelist([X|List1],[Y|List2],[Y|List]) :-
        mergelist([X|List1],List2,List).

noconsecs([]).
noconsecs([H]).
noconsecs([H1,H2|T]):- H2-1=:=H1,!,fail;
                       noconsecs([H2|T]).

bubblesort(InputList,SortList) :-
        swap(InputList,List) , ! ,
        bubblesort(List,SortList).
bubblesort(SortList,SortList).

swap([X,Y|List],[Y,X|List]) :- X > Y.
swap([Z|List],[Z|List1]) :- swap(List,List1).

