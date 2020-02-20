digitos [0-9][0-9]
separador1 (\.\.\.)

%%

({digitos}{separador1}){3}{digitos} {
    printf("O separador é: ...\n");
    printf("%s\n", yytext);
}

({digitos}-){3}{digitos} {
    printf("O separador é: -\n");
    printf("%s\n", yytext);
}

({digitos}:){3}{digitos} {
    printf("O separador é: :\n");
    printf("%s\n", yytext);
}

.* printf("INVÁLIDO\n");
