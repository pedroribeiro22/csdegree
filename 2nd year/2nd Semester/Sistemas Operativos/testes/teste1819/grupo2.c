#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>

void get_email(char *email, char *linha) {
    // copiar a parte do número de aluno
    for(int i = 0; i < 6; i++)
        email[i] = linha[i];

    // adicionar a parte '@alunos.uminho.pt'
    sprintf(email + 6, "%s", "@alunos.uminho.pt");
}

int main(int argc, char **argv) {
    // File descryptor
    int fd;

    // Vamos manter o número de programas concorrentes
    // Não pode exceder 10
    int concorrentes = 0;
    int commPipe[2];
    char *readBuffer = malloc(sizeof(char) * 12);
    char *email = malloc(sizeof(char) * 50);


    // Caso acho ficheiro abrimos
    if((fd = open(argv[1], O_RDONLY, 0666)) == -1) return -1;

    // O exercício propriamente dito
    while(read(fd, readBuffer, 10)) {
        if(concorrentes == 10) {
            wait(NULL);
            concorrentes--;
        }

        pipe(commPipe);
        write(commPipe[1], readBuffer, 10);
        close(commPipe[0]);

        if(fork() == 0) {
            close(commPipe[1]);
            dup2(commPipe[0], 0);
            close(commPipe[0]);
            get_email(email, readBuffer);
            execlp("ls", "ls", NULL);
        }
        close(commPipe[1]);
        concorrentes++;
    }
    return 0;
}
