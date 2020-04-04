%{
int yylex();
void yyerror(char *);
#include <stdio.h>
%}

%token INICIO FIM PAL NUM

%%

Frase : INICIO Lista FIM { printf("ok\n"); }
      ;

Lista : Elem
      | Elem ',' Lista
      ;

Elem : pal
     | num
     ;

%%

int main() {
    yyparse();
    return 0;
}

void yyerror(char *s) {
    fprintf(stderr, "Linha %d: %s (%s)\n", yylineno, yytext);
}
