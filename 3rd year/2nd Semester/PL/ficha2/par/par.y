%{
#include <stdio.h>
extern int yylex();
%}

%token ERRO

%%
Par: '(' Par ')' Par 
    |
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