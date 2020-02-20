%{
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

float sum = 0;
int summing = 0;

%}

%%

[0-9]+ if(summing) sum += atof(yytext);

\+ summing ? (summing = 0) : (summing = 1);

= printf("CURRENT SUM: %f\n", sum);

.|\n ; 
