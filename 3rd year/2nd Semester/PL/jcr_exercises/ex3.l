%{
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
%}

%%

.*\d.*$ {

    int summing = 0;
    int total = 0;
    for(int i = 0; yytext[i] != '\n'; i++) {
        if(yytext[i] == '=') printf("%d\n", total);
        if(yytext[i] == '+') {
            if(!summing) summing = 1;
            else summing = 0;
        }    
        if(summing && isdigit(yytext[i])) total += (((int) yytext[i]) - 48);
    }
    printf("%d\n", total);

    // Está a ver um carater de cada vez, não funciona bem para número com
    // mais de um dígito.

}

.+$ ;
