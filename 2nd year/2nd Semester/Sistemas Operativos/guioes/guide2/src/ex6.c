#include "guide2.h"


int main(int argc, char **argv) {
    int goal;
    int matriz[LINHAS][COLUNAS];
    srand(time(NULL));
    // vamos definir uma top range para gerar os valores da matriz;
    int range = 10;
    if(argc < 2) {
        printf("Please provide a target number for me to lookup.\n");
        exit(1);
    } else {
        goal = atoi(argv[1]);
    }
    // povoar a matriz
    for(int i = 0; i < LINHAS; i++)
        for(int j = 0; j < COLUNAS; j++)
            matriz[i][j] = rand() % range;

    int filhos[LINHAS];
    for(int i = 0; i < LINHAS; i++) {
        if(!(filhos[i] = fork())) {
            for(int j = 0; j < COLUNAS; j++) {
                if(matriz[i][j] == goal)
                    _exit(1);
            }
        }
        _exit(0);
    }

    int processId;
    int status;
    while((processId = wait(&status)) != -1) {
        int k;
        for(k = 0; k < LINHAS && processId != filhos[k]; k++) {
            if(WEXITSTATUS(status)) printf("O filho %d encontrou o 'goal' na linha %d\n", processId, k);
        }
    }
    return 0;
}
