%{
#include <stdio.h>
#include <string.h>
extern int yylex();
int yyerror(char *s);
%}

%union {
    float fvalue;
    char *svalue;
}


%token ERRO TRUE FALSE NULLVALUE string number
%type <fvalue> number
%type <svalue> string Value Obj PairList Pair Array

%%

Json
    : Value                 { printf("%s\n", $1); }
    ;

Obj
    : '{' '}'               { asprintf(&$$, "<objecto/>"); }
    | '{' PairList '}'      { asprintf(&$$, "<objecto>\n%s\n</objecto>", $2); }
    ;

PairList
    : PairList ',' Pair     { asprintf(&$$, "%s\n%s", $1, $3); }
    | Pair                  { $$ = strdup($1); }
    ;

Pair
    : string ':' Value      { asprintf(&$$, "<%s>%s<%s/>", $1, $3, $1); }
    ;

Array
    : '[' ']'               { asprintf(&$$, "<lista/>"); }
    | '[' ValueList ']'     { asprintf(&$$, "<lista>\n%s\n</lista>", $2); }
    ;

ValueList
    : ValueList ',' Value   { asprintf(&$$, "%s\n%s", $1, $3); }
    | Value                 { asprintf(&$$, "<item>%s</item>", $1 ); }
    ;

Value
    : string                { asprintf(&$$, "<item>%s</item>", $1); }
    | number                { asprintf(&$$, "<item>%f</item>", $1); }
    | Obj                   { asprintf(&$$, "%s", $1); }
    | Array                 { asprintf(&$$, "%s", $1); }
    | TRUE                  { asprintf(&$$, "<item>true</item>"); }
    | FALSE                 { asprintf(&$$, "<item>false</item>"); }
    | NULLVALUE             { asprintf(&$$, "<item>null</item>"); }
    ;

%%

int main() {
    yyparse();
    return 0;
}