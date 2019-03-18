#include "guide2.h"

int main(int argc, char **argv) {

    for(int i = 1; i < 11; i++) {
        if(!fork()) {
            printf("'processID' (filho) : %d\n, 'processID' (pai): %d\n", getpid(), getppid());
            _exit(i); // might as well iniciar o `i` a 1 para cumprir as especificações do enunciado
        }
    pid_t pid;
    int status;
    pid = wait(&status);
    status = WEXITSTATUS(status);
    printf("'processID': %d terminou com o código: %d\n", pid, status);
    }
    return 0;
}