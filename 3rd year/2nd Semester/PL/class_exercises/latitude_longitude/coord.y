%{
#include <stdio.h>
extern int yylex();
int yyerror();
%}

%union{
    float vreal;
}

%token erro numero
%type <vreal> numero

%%

ListaCoord: ListaCoord Coord
      | Coord
      ;

Coord: '(' num ',' num ')' {
     if($2 >= -90 && $2 <= 90 && $4 >= -180 && $4 <= 180) printf("Válido\n");
     else printf("Inválido\n");
}
     ;

num: numero

%%
int main() {
    yyparse();
    return 0;
}

int yyerror() {
    print("Erro sintático...\n");
    return 0;
}
