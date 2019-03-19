#include "guide3.h"

// Executar o programa enquanto não mandar um zero cá para fora
int body(const char *program) {
    int status;
    int saida = 0;
    int vezes = 0;
    while(saida == 0) {
        if(!fork()) {
            execl(program, program, NULL);
        }
        wait(&status);
        if(WIFEXITED(status) && WEXITSTATUS(status) == 0) saida = 1;
        vezes++;
    }
    return vezes; // numero de vezes que o programa executou
}


