%{
#include <string.h>
%}

sinal (\+|-)
parte_inteira [0-9]+
parte_decimal \.[0-9]+

%%

\({sinal}?{parte_inteira}{parte_decimal}?,\ {sinal}?{parte_inteira}{parte_decimal}?\)$ {


    // usar strtok
    int valid = 1;
    int fst_value;
    int i;
    if(yytext[1] != "+" || yytext[1] != "-") fst_value = atof(yytext + 1);
    else fst_value = atof(yytext + 2);
    for(i = 0; yytext[i] != ","; i++); 
    int snd_value = atof(yytext + i);
    if(fst_value < -90 || fst_value > 90) valid = 0;
    if(snd_value < -180 || snd_value > 180) valid = 0;
    if(valid) printf("%s\n", yytext);

}

.* printf("INVÁLIDO: %s\n", yytext);
