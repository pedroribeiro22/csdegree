%{
#include <stdio.h>
extern int yylex();
int error();
%}

%token INT
%token DOISPONTOS
%token ERRO

%%
SeqListas: Lista SeqListas
    |
    ;

Lista: '[' ']' 
    | '[' Elems ']'
    ;

Elems: Elem
    | Elem ',' Elems
    ;

Elem: INT
    | Lista
    | Intervalo
    ;

Intervalo: INT DOISPONTOS INT
    ;



%%

int main() {
    yyparse();
    return 0;
}

int yyerror() {
    printf("Erro sintático...\n");
    return 0;
}