#include "guide5.h"

int main() {
    int fds[2];
    char buf[10];
    if(pipe(fds) == -1) return -1;
    if(fork() == 0) {
        dup2(fds[1], 1);
        close(fds[0]);
        execlp("ls", "ls", "/etc", NULL);
    }
    if(fork() == 0) {
        dup2(fds[0], 0);
        close(fds[1]);
        execlp("wc", "wc", "-l", NULL);
    }
    close(fds[0]);
    close(fds[1]);
    return 0;
}