#include "guide2.h"

// praticamente igual ao 7, só muda na parte de esperar pelos processos
#define COLUNAS 100
#define LINHAS 10

void povoaMatriz(int matriz[10][100]) {
    int ground = 0;
    int ceiling = 10;
    srand(time(NULL));
    for(int i = 0; i < LINHAS; i++)
        for(int j = 0; j < COLUNAS; j++)
            matriz[i][j] = rand() % (ceiling + 1 - ground) + ground;
}

int main(int argc, char **argv) {
    int alvo;
    int matrix[LINHAS][COLUNAS];
    alvo = atoi(argv[1]);
    povoaMatriz(matrix);
    // podemos ir guardando o `processID` dos filhos num array
    int childs[LINHAS];
    for(int i = 0; i < LINHAS; i++) {
        if(!(childs[i] = fork())) {
            for(int j = 0; j < COLUNAS; j++) {
                if(matrix[i][j] == alvo) _exit(1);
            }
            _exit(0);
        }
    }
    int i = 0; // vai servir para coordenar a espera
    pid_t pid;
    int status;
    while(i < LINHAS) {
        waitpid(childs[i], &status, 1);
        if(WEXITSTATUS(status)) printf("O processo %d encontrou o alvo na linha %d\n", childs[i], i);
        i++;
    }
    return 0;
}
