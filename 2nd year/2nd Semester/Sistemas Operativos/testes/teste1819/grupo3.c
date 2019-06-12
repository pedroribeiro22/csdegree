#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>

int main(int argc, char **argv) {

    // Cria named pipe
    mkfifo("stdout", 0666);
    int fd = open("stdout", O_WRONLY, 0666);
    char *buf = malloc(sizeof(char) * 128);
    int concorrentes = 0;
    int commPipe[2];
    int padroes = 0;
    while(read(0, buf, 128) != 0) {
        if(concorrentes == 8) {
            wait(NULL);
            concorrentes--;
        }
        pipe(commPipe);
        write(commPipe[1], buf, 128);
        close(commPipe[1]);
        if(fork() == 0) {
            close(commPipe[1]);
            dup2(commPipe[0], 0);
            dup2(fd, 1);
            execlp("X", "X", NULL);
            _exit(0);
        }
        close(commPipe[0]);
        concorrentes++;
    }
    char c;
    int newFd = open("stdout", O_RDONLY, 0666);
    while(read(newFd, &c, 1)) padroes++;
    return padroes;
}
