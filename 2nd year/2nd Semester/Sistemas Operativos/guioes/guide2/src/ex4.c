#include "guide2.h"

int main(int argc, char **argv) {
    int processId, status;
    for(int i = 0; i < 10; i++) {
        if(fork() == 0) {
            /*  Se estivermos no processo-filho  */
            _exit(i);
        }
    }
    while((processId = wait(&status)) != -1) {
        printf("O processo %d morreu com o código %d\n", processId, WEXITSTATUS(status));
    }
    return 0;
}
