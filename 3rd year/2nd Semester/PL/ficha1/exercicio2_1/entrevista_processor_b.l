%%

(E|e)(L|e)(E|e):.*|(E|e)(U|u):.* {

    int i = 0;
    while(yytext[i] != ':') i++;
    i++;
    // se o 'i' for 4 entao foi 'ELE', se for 3 entao foi 'EU'

    (i == 4) ? printf("%s", "Entrevistador:") : printf("%s", "Entrevistado:");
    printf("%s", yytext + i);
}
. ;
