%%

(E|e)(L|l)(E|e):.*|(E|e)(U|u):.* {

    int i = 0;
    while(yytext[i] != ':') i++;
    // ignorar o ':'
    i++;
    while(yytext[i] != '\0') {
        printf("%c", yytext[i]);
        i++;
    }
}
. ;
