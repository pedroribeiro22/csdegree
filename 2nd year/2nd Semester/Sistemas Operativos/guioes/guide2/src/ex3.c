#include "guide2.h"


int main(int argc, char **argv) {

    for(int i = 0; i < 11; i++) {
        if(fork() == 0) {
            printf("pid : %d\n", getpid());
            printf("ppid : %d\n", getppid());
            _exit(i);
        }
    pid_t pid;
    int status;
    pid = wait(&status);
    status = WEXITSTATUS(status);
    printf("pid : %d terminou com o codigo : %d\n", pid, status);
    }
}