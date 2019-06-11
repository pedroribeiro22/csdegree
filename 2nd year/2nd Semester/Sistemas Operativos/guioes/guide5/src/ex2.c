#include "guide5.h"

int readCoisas() {
    char buff[BUFSIZ];
    int fds[2];
    int r;
    int status;
    if(pipe(fds) == -1) return -1;
    switch(fork()) {
        case -1:
            perror("Fodeu fork\n");
        case 0:
            close(fds[1]);
            while(read(fds[0], buff, BUFSIZ) != 0);
            if(r > 0) printf("Filho: %s \n");
            close(fds[0]);
            _exit(0);
        case 1:
            close(fds[0]);
            r = write(fds[1], buff, BUFSIZ);
            if(r > 0) printf("Pai: %s \n", buff);
            close(fds[1]);
            wait(&status);
        default:
            break;
    }
}

int main() {
    readCoisas();
    return 0;
}