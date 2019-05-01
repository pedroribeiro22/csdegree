#include "guide5.h"

int main() {

    int fds[2];
    char ** commands[4];
    char **grep = { "grep", "-v", "^#", "/etc/passwd", NULL };
    char **cut  = { "cut", "-f7", "-d:", NULL };
    char **unic = { "uniq", NULL };
    char **wc   = { "wc", "-l", NULL };
    commands[0] = grep;
    commands[1] = cut;
    commands[2] = unic;
    commands[3] = wc;
    int saver = 0;
    for(int i = 0; i < 4; i++) {
        if(i < 3) pipe(fds);
        if(fork() == 0) {
            if(i > 0) { // tenho de guardar o extremo de leitura
                dup2(saver, 0);
                close(saver);
            }
            if(i < 3) { // se nao e o ultimo
                dup2(fds[1], 1);
                close(fds[0]);
                close(fds[1]);
            }
            execvp(commands[i][0], commands[i]);
            _exit(1);
        }
        if(i < 3) close(fds[1]);
        if(i > 0) {
            close(saver);
        saver = fds[0];
    }
    return 0;
}