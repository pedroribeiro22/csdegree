%code {
#include <stdio.h>
int yyerror(char *s);
}

%union { int n; }
%token INT
%type <n> INT ints

%%

lista : '(' ints ')'        {
    printf("cheguei ao fim %d!\n", $2);
    return 0;
}

ints : INT              { $$ = $1; }
     | INT ',' ints     { $$ = $1 + $3; }
     ;

%%


int main() {
    yyparse();
    return 0;
}

int yyerror(char *s) {
    printf("erro: %s\n", s);
}
