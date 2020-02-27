%{
#include <string.h>
#include <stdio.h>
%}

%%

^(E|e)(L|l)(E|e): {
    /*
    const char inicio[5] = "ELE:";
    char *token;
    token = strtok(yytext, inicio);
    */
    printf("%s\n", "1");
}


^(E|e)(U|u): {
    /*
    const char inicio[5] = "EU:";
    char *token;
    token = strtok(yytext, inicio);
    */
    printf("%s\n", "2");
}

^.\n$ ;
