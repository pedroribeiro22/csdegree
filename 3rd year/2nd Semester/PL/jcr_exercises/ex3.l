%{
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

float sum = 0;
int summing = 0;

%}
%x somar
%%
    BEGIN 0;

\+ BEGIN somar;
<somar>[0-9]+ sum += atoi(yytext);
<somar>\+ BEGIN 0;
<*>= printf("soma = %d\n", sum);
<*>.|\n ;
%%
int yywrap() {
    printf("A soma é: %d\n", sum);
}