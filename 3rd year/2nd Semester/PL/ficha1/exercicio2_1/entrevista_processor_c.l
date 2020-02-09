%%

(E|e)(L|l)(E|e)|(E|e)(U|u).* {

    char *entrevistado = malloc(256);
    char *entrevistador = malloc(256);
    int i = 0;

    // 0 -> linha_entrevistado
    // 1 -> linha_entrevistador

    int line_id = (yytext[1] == 'U' || yytext[1] == 'u') ? 0 : 1;

    while(yytext[i] != ':' && yytext[i] != '=') i++;

    // estamos no ':' ou no '='

    if(yytext[i] == '=') {
        int j = 0;
        if(line_id == 0) {
            while(yytext[i] != '\n' && yytext[i] != '\0') {
                entrevistado[j++] = yytext[i++];
            }
        } else {
            while(yytext[i] != '\n' && yytext[i] != '\0') {
                entrevistador[j++] = yytext[i++];
            }
        }
    } else {
        if(line_id == 0) {
            printf("%s", entrevistado);
            printf("%s", yytext + i);
        } else {
            printf("%s", entrevistador);
            printf("%s", yytext + i);
        }
    }

    // TODO: não estã a gravar corretamento o nome dos entervenientes na entrevista. É preciso
    // rever isto com a devida atenção.

}

. ;
