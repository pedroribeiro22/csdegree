%%

.+\/((S|s)(S|s)(E|e)|(M|m)(B|b)|(Q|q)(Q|q)).+ {
    int i = 0;
    while(yytext[i] != '\n' && yytext[i] != '\0') {
        if(yytext[i] != '\') printf("%c", yytext[i++]);
        printf("%c", yytext[i++]);
        switch(yytext[i]) {
            case 'q':
                printf("%s", "qualquer");
                i += 2;
                break;
            case 'm':
                printf("%s", "muito bom");
                i += 2;
                break;    
            case 's':
                printf("%s", "se e só se");
                i += 3;
                break;    
        }
    } 

}

. ;
