%%
^(_|\.)\d+([a-zA-Z]){3,}(_|[a-zA-Z])$ printf("%s: VÁLIDO\n", yytext); 

.+$ printf("%s: INVALIDO\n", yytext);

\n ;