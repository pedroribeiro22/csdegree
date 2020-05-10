%{
#include <stdio.h>
#include <string.h>
extern int yylex();
int yyerror(char *s);
%}

%union {

}

%token READ PRINT SHOW num id

%%

Calc:
    ListaComandos
    ;

ListaComandos:
    ListaComandos Comando
    | Comando
    ;

Comando:
    Print
    | Read
    | Show
    | Atrib
    ;

Print:
     PRINT Exp
     ;


%%

int main() {
    yyparse();
    return 0;
}

int yyerror(char *s) {
    printf("Erro sintático: %s\n", s);
    return 0;
}
