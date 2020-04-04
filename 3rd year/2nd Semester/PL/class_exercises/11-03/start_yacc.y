%{
#include <stdio.h>
int yylex();
int yyerror(char *s);
%}

%%

lista : '(' ints ')' { printf("fim\n"); return 0; }

ints : i
     | i ',' ints
     ;

i : d i
  | d
  ;

d : '0'
  | '1'
  | '2'
  | '3'
  | '4'
  | '5'
  | '6'
  | '7'
  | '8'
  | '9'
  ;

%%

int yylex() {
    return getchar();
}

int main() {
    yyparse();
    return 0;
}

int yyerror(char *s) {
    printf("erro: %s\n", s);
}
