#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// COLORS
#define NRM  "\x1B[0m"
#define RED  "\x1B[1;31m"
#define YEL  "\x1B[33m"
#define BLUE "\x1B[34m"
#define GRN  "\x1B[32m"

// NUMERO DE OPÇÕES DE TAREFAS A PEDIR
#define NTASKS 10

// TEXTO A IMPRIMIR PARA O UTILIZADOR ESCOLHER UMA OPÇÃO
static char* tasks[NTASKS] = {"PRODUTOS ENVOLVIDOS",
                              "CLIENTES ENVOLVIDOS",
                              "VENDAS EFETIVAS",
                              "ULTIMO CLIENTE",
                              "NÚMERO DE VENDAS REGISTADAS PARA ESTE CLIENTE",
                              "NÚMERO DE VENDAS NA FILIAL 1",
                              "NÚMERO DE VENDAS NA FILIAL 2",
                              "LINHA MAIS LONGA",
                              "NÚMERO DE CLIENTE COM CÓDIGO COMEÇADO POR 'A'",
                              "FACTURAÇÃO TOTAL REGISTADA"};

// GREET USER
void greetUser() {
    printf("\n" BLUE "PRESSIONE UM DOS NÚMEROS QUE ANTECEDEM AS OPÇÕES PARA SELECIONAR UMA TAREFA A EXECUTAR: \n\n\n");
    for(int i = 0; i < NTASKS; i++) {
        printf(RED "(%d)" "-> " YEL "%s\n", i+1, tasks[i]);
    }
    printf("\n");
    int option;
    scanf("%d", &option);
    printf("\n");
    // PARA JÁ VAZIO, DEPOIS EXECUTARA FUNÇÕES
    printf(YEL "ESCOLHEU A OPÇÃO: " GRN "'%d (%s)'\n", option, tasks[option - 1]);
}







int main() {
    greetUser();
    return 0;
}
