#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>

ssize_t readln(int fildes, void *buf, size_t nbyte) {

    ssize_t size = 0;
    char c;
    char *buff = (char *) buf;
    while(size < nbyte && read(fildes, &c, 1) == 1) {
        buff[size++] = c;
        if(c == '\n') {
            return size;
        }
    }
    return size;
}

int main(int argc, char **argv) {

    int n = argc - 1;
    char linha[128];
    for(int i = 1; i < argc; i++) {
        sprintf(linha, "%d", i);
        mkfifo(linha, 0666);
    }

    for(int i = 0; i < n; i++) {
        if(fork() == 0) {
            sprintf(linha, "%d", i + 1);
            int fd = open(linha, O_RDONLY, 0666);
            dup2(fd, 1);
            close(fd);
            execlp(argv[1], argv[1], NULL);
        }
    }

    // rodar enquanto houber linhas para ler e mostrar
    // 10 de cada vez
}

