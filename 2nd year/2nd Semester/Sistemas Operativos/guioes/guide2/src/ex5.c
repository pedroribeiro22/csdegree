#include "guide2.h"

int main(int argc, char **argv) {
    int processId, status;
    for(int i = 0; i < 10; i++) {
        if(fork() == 0) {
            printf("[proc#%d]: Sou o filho (tenho id: %d) e o meu pai é %d.\n", i, getpid(), getppid());
        } else {
            pid_t terminated_pid = wait(&status);
            if(WIFEXITED(status)) {
                printf("[pai] process %d exited. exit code: %d\n", terminated_pid, WEXITSTATUS(status));
            } else {
                printf("[pai]: exited", terminated_pid);
            }
        }
    }
    while((processId = wait(&status)) != -1) {
        printf("Sou o %d e o %d morreu.\n", getpid(), processId);
    }
    return 0;
}


