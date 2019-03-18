#include "guide2.h"

int main() {
    

    if(!fork()) {
        // processo filho
        printf("Este é o processo filho: L1\nIdentificador do processo: %d\nIdentificador do processo pai: %d\n", getpid(), getppid());
    } else {
        // processo pai
        printf("Este é o processo pai: L0\nIdentificador do processo: %d\nIdentificador do processo pai: %d\n", getpid(), getppid());
    }

    return 0;

}