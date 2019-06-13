#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>

void get_email(char *email, char *linha) {
    for(int i = 0; i < 6; i++)
        email[i] = linha[i];
    sprintf(email + 6, "%s", "@alunos.uminho.pt");
}

int main(int argc, char **argv) {
    char *buf = malloc(sizeof(char) * 10);
    char *email = malloc(sizeof(char) * 100);
    int fd = open(argv[1], O_RDONLY, 0666);
    int concorrentes = 0;
    int paipe[2];
    pipe(paipe);
    while((read(fd, buf, 10)) > 0) {
        if(concorrentes == 10) {
            wait(NULL);
            concorrentes--;
        }
        write(paipe[1], buf, 10);
        close(paipe[0]);
        if(fork() == 0) {
            close(paipe[1]);
            dup2(paipe[0], 0);
            close(paipe[0]);
            get_email(email, buf);
            execlp("mail", "-s", "Sistemas_Operativos", email);
        }
        close(paipe[1]);
        concorrentes++;
    }
}
