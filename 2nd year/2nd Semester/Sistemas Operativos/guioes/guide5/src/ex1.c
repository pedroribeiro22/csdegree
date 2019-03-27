#include "guide5.h"

int filho2pai() {
    int fds[2];
    int status;
    char texto[] = "U guys wanted to party!!!!";
    char leitura[1024];
    if(pipe(fds) == -1) return -1;
    switch(fork()) {
        case -1:
            perror("Fodeu fork\n");
        case 0:
            close(fds[0]);
            write(fds[1], &texto, sizeof(texto));
            printf("[FILHO]: Wrote line to pipe\n");
            close(fds[1]);
            _exit(0);
        default:
            close(fds[1]);
            read(fds[0], &leitura, sizeof(texto));
            printf("[PAI]: read %s from pipe\n", leitura);
            close(fds[0]);
            wait(&status);
    }
    return 0;
}

int pai2filho() {
    int fds[2];
    int status;
    char texto[] = "És um boi!";
    char leitura[1024];
    if(pipe(fds) == -1) return -1;
    switch(fork()) {
        case -1:
            perror("Fodeu fork\n");
        case 0:
            close(fds[1]);
            read(fds[0], &leitura, sizeof(leitura));
            printf("[FILHO]: read %s from pipe\n", leitura);
            close(fds[0]);
            _exit(0);
        default:
            close(fds[0]);
            write(fds[1], &texto, sizeof(texto));
            printf("[PAI]: Wrote line to pipe\n");
            close(fds[1]);
            wait(&status);
    }
    return 0;
}

int main(int argc, char **argv) {
    int r = filho2pai();
    printf("%d\n", r);
    return 0;
}
