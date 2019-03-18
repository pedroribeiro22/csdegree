#include "guide2.h"

int main(int argc, char **argv) {

    for(int i = 1; i < 11; i++) {
        if(!fork()) {
            // printf("Processo: %d  ->  Pai: %d\n", getpid(), getppid());
        } else break;
    }
    pid_t pid;
    int status;
    while((pid = wait(&status)) != -1) 
        printf("Processo: %d -> O %d morreu\n", getpid(), pid);
    
    return 0;
}