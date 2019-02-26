#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pai, filho;
    filho = getpid();
    pai = getppid();
    printf("O pai é %d e o filho é %d.\n", pai, filho);
    return 0;
}