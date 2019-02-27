#include "guide2.h"

int main(int argc, char **argv) {
    
    for(int i = 0; i < 10; i++) {
        if(!fork()) {
            printf("PID(L1): %d\nPID(L0): %d\n", getpid(), getppid());
            _exit(i);
        }
        int pid, status;
        pid = wait(&status);
        status = WEXITSTATUS(status);
        printf("O filho é %d terminou com o código %d\n", pid, status);
    }
    return 0;
}