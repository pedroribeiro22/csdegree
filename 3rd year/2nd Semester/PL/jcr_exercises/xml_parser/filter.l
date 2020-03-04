%{

int tags_abertura = 0;
int tags_fecho = 0;
int atributos = 0;
int comentarios = 0;
int vazios = 0;

%}

%x tag_abertura
%x xml

qname [a-zA-Z][a-zA-Z0-9_\-\.:]*
atributo {qname}{ws}*={ws}*\"[^"]+\"
ws [ \t]
%%

BEGIN 0;

<*>\<\?xml[^?]+\?\> BEGIN xml;

<*>[ \t\n] ;

<xml>\<!\-\-[^\-]*\-\-\> comentarios++;

<xml>\<\/[^>]+\> {
    tags_fecho++;
}    

<xml>\<{qname} {
    BEGIN tag_abertura;
}

<tag_abertura>\/\> {
    vazios++;
    BEGIN xml;
}

<tag_abertura>\> {
    tags_abertura++;
    BEGIN xml;
}    

<tag_abertura>{atributo} atributos++;

<<EOF>> {
    printf("\nTags de abertura:\t%d\n", tags_abertura);
    printf("Tags de fecho:\t\t%d\n", tags_fecho);
    printf("Atributos:\t\t%d\n", atributos);
    printf("Comentários:\t\t%d\n", comentarios);
    printf("Vazios:\t\t\t%d\n", vazios);
    return 0;
}

%%
