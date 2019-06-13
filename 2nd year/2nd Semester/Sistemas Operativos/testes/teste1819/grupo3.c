#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>

int main(int argc, char **argv) {
    int commPipes[8][2];
    char *buf = malloc(sizeof(char) * 128);
    for(int i = 0; i < 8; i++) {
        pipe(commPipes[i]);
    }
    mkfifo("common", 0666);
    int out = open("common", O_RDWR, 0666);
    int r = 0;
    while(read(0, buf, 128) == 128) {
        write(commPipes[r][1], buf, 128);
        r = (r + 1) % 8;
    }

    for(int i = 0; i < 8; i++) {
        if(fork()) {
            char *temp = malloc(sizeof(char) * 128);
            while(read(commPipes[i][0], temp, 128) == 128) {
                if(fork() == 0) {
                    dup2(out, 1);
                    execlp("patgrep", "patgrep", NULL);
                }
            }
        }
    }
    close(out);
    int newOut = open("common", O_RDONLY, 0666);
    int counter = 0;
    char c;
    while(read(newOut, &c, 1) == 1) counter++;
    return counter;
}
