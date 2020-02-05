%%
[0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1]) {
    int month = atoi(yytext + 5);
    int day = atoi(yytext + 8);
    int days_per_month[12] = {
        31,
        28,
        31,
        30,
        31,
        30,
        31,
        31,
        30,
        31,
        30,
        31
    };
    if(day <= days_per_month[month - 1]) printf("%s", yytext);
    }
. ;
