#include "ex8.h"
#include <stdio.h>

extern int yylex();

int main() {

    int prox_simbol;

    while((prox_simbol = yylex())) {

        switch(prox_simbol) {

            case INT:
                printf("INT ");
                break;
            case OP:
                printf("OP ");
                break;
            case AP:
                printf("AP ");
                break;
            case FP:
                printf("FP ");
                break;
            default:
                break;

        }
    }
}