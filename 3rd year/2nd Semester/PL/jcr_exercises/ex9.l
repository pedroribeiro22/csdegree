%{

int balance_cents = 0;

%}
%x on taking_coins 
%% 
    BEGIN 0;

LEVANTAR BEGIN on;

<on>T=(601|641)[0-9]{6} printf("Os números vermelhos são proibídos!\n");

<on>T=00[0-9]+ {
    if(balance_cents) >= 150 {
        balance_cents -= 150;
    } else printf("Não tem saldo suficiente para realizar a chamada!\n");
}

<on>T=2[0-9]{8} {
    if(balance_cents) >= 25 {
        balance_cents -= 25;
    } else printf("Não tem saldo suficiente para realizar a chamada!\n");
}

<on>T=800[0-9]{6} ;

<on>T=808[0-9]{6} {
    if(balance_cents) >= 10 {
        balance_cents -= 10;
    } else printf("Não tem saldo suficiente para realizar a chamada!\n");
}

<on>POUSAR {
    printf("Troco: %de%dc", balance_cents/100, balance_cents%100);
    balance_cents = 0;
    BEGIN 0;
}

<on>MOEDA BEGIN taking_coins;

<taking_coins>MOEDA

<taking_coins>\. {
    printf("Saldo: %de%dc, balance_cents/100, balance_cents%100);
    BEGIN on;
}

<taking_coins>,  ;

<taking_coins>10c balance_cents += 10;

<taking_coins>20c balance_cents += 20;

<taking_coins>50c balance_cents += 50;

<taking_coins>1e  balance_cents += 100;

<taking_coins>2e  balance_cents += 200;

<taking_coins>[^,\. \n\t]+ printf"Moeda inválida: %s\n", yytext);
