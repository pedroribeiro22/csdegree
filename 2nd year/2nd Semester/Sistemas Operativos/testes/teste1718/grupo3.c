#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>
#include <sys/types.h>

int getDigito(char *s) {
    char num[4096];
    int i = 0;
    while(s[i] != ' ') {
        num[i] = s[i];
        i++;
    }
    num[i] = '\0';
    return atoi(num);
}

int main(int argc, char **argv) {

    if(argc < 3) return -1;

    int p = atoi(argv[1]);
    int c = atoi(argv[2]);

    int pipeProdutores[2];
    pipe(pipeProdutores);

    for(int i = 0; i < p; i++) {
        if(fork() == 0) {
            close(pipeProdutores[0]);
            dup2(pipeProdutores[1], 1);
            close(pipeProdutores[1]);
            execlp("produtor", "produtor", NULL);
            _exit(0);
        }
    }
    close(pipeProdutores[1]);

    int pipeConsumidores[c][2];

    for(int i = 0; i < c; i++) {
        pipe(pipeConsumidores[i]);
        if(fork() == 0) {
            for(int j = 0; j < i; i++) {
                close(pipeConsumidores[j][0]);
                close(pipeConsumidores[j][1]);
            }
            close(pipeConsumidores[i][1]);
            dup2(pipeConsumidores[i][0], 0);
            close(pipeConsumidores[i][0]);
            execlp("consumidor", "consumidor", NULL);
            _exit(0);
        }
        else close(pipeConsumidores[i][0]);
    }

    char buf[4096];
    int size;

    while(size = readln(pipeProdutores[0], buf, 4096) > 0) {
        int consumidor = getDigito(buf);
        write(pipeConsumidores[c][1], buf + sizeof(int), size - sizeof(int));
    }

    for(int i = 0; i < c; i++)
        close(pipeConsumidores[i][1]);
    close(pipeProdutores[0]);
    return 0;
}
