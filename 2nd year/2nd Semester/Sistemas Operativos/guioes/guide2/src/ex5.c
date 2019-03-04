#include "guide2.h"

int main(int argc, char **argv) {
    int processId, status;
    for(int i = 0; i < 10; i++) {
        if(fork() == 0) {
            printf("Sou o filho (tenho id: %d) e o meu pai é %d.\n", getpid(), getppid());
        } else break;
    }
    while((processId = wait(&status)) != -1) {
        printf("Sou o %d e o %d morreu.\n", getpid(), processId);
    }
    return 0;
}


