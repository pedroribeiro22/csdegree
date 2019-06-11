#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <stdio.h>

int main(int argc, char **argv) {

    for(int i = 1; i < 11; i++) {
        if(fork() == 0)
            _exit(i);
    }

    int pid, status;
    while((pid = wait(&status)) != -1) {
        printf("%d terminou\n", pid);
    }
}
