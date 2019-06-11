#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>
#include <sys/types.h>

int main(int argc, char **argv) {

    if(argc < 3) return -1;

    // Número de produtores
    int p = atoi(argv[1]);

    // Número de consumidores
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
            for(int p = 0; p < i; p++) {
                close(pipeConsumidores[p][0]);
                close(pipeConsumidores[p][1]);
            }

            close(pipeConsumidores[i][1]);
            dup2(pipeConsumidores[i][0], 0);
            close(pipeConsumidores[i][0]);
            execlp("consumidor", "consumidor", NULL);
            _exit(0);
        }
        else close(pipeConsumidores[i][0]);
    }
}
