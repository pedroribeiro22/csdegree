#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t id;
    for(int i = 0; i < 10; i++) {
        if((id = fork()) == 0) {
            printf("O filho é %d e o pai é %d.\n", getpid(), getppid());
        } else {
            wait(&id);
        }
    }
    return 0;
}