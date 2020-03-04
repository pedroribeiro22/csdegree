%{
    char* id;
%}

%s tag_abertura
%s xml

qname [a-zA-Z][a-zA-Z0-9_\-\.:]*
atributo {qname}{ws}*={ws}*\"[^"]+\"
ws [ \t]*

%%

BEGIN 0;

<*>\<\?xml[^?]+\?\> {
    printf("XML\n");
    BEGIN xml;
}    

<*>[ \t\n] ;

<xml>\<!\-\-[^\-]*\-\-\> ;

<xml>[^\<]+ printf("- %s\n", yytext);

<xml>\<\/[^>]+\> {
    yytext[yyleng-1] = '\0';
    printf(")%s\n", yytext + 2);
}    

<xml>\<{qname} {
    id = strdup(yytext + 1);
    printf("%s\n", yytext + 1);
    BEGIN tag_abertura;
}

<tag_abertura>\/\> {
    printf(")%s\n", id);
    BEGIN xml;
}

<tag_abertura>\> {
    BEGIN xml;
}    

<tag_abertura>{atributo} {

}

<<EOF>> {
    printf("Cabou");
    return 0;
}

%%
