%{
#include <string.h>
#include <stdlib.h>
%}

%%

([0-9]{1,3}\.){3}([0-9]{1,3})$ {

    const char s[2] = ":";
    char *token;
    int valid = 1;
    token = strtok(yytext, s);
    while(token != NULL) {
        token = strtok(NULL, s);
        int to_test = atoi(token);
        if(to_test > 255) {
            printf("Invalid, yet formally correct IPv4 address!\n");
            valid = 0;
        }    
    }
    if(valid) printf("IPv4 valid address: %s\n", yytext);

}

([0-9a-f]{1,4}:){7}([0-9a-f]{1,4})$ {

    printf("IPv6 valid address: %s\n", yytext);
}

.+$ ;
