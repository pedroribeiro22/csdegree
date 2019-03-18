#include "guide2.h"

int main(int argc, char **argv) {

    for(int i = 1; i < 11; i++) {
        if(!fork())
            // possível código que queiramos executar
            _exit(i);
    }
    pid_t pid;
    int status;
    while((pid = wait(&status)) != -1)
        printf("%d morreu com o código de saída: %d\n", pid, WEXITSTATUS(status));
    return 0;

}