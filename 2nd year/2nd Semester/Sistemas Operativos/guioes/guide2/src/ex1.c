#include "guide2.h"

int main() {
    pid_t pai, filho;
    filho = getpid();
    printf("O 'processID' do filho é %d.\n", filho);
    pai = getppid();
    printf("O 'processID' do pai é %d.\n", pai);
    /* Para comprovar que o processo pai é o terminal, executar `ps` */
    return 0;
}