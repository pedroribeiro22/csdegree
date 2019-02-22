#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>

/*  Re-implementação da função readln */
ssize_t readln(int fildes, void* buf, ssize_t nbyte) {
    ssize_t r = 0;
    char c;
    char* buff = (char *) buff; // cast
    while(r < nbyte && read(fildes, &c, 1) == 1) {
        if(c == '\0')
            return r;
        buff[r++] = c;
        if(c == '\n')
            return r;
    }
    return r;
}

/*  Exercício 8 */
void myFileCat(char *filename) {
    char byte;
    int fp = open(filename, O_CREAT | O_RDONLY);
    int r;
    do {
        r = read(fp, &byte, 1);
        write(1, &byte, 1);
    }
    while(r != 0);
}

/* Exercício 9 */
void myHead(char *filename) {
    int newlines = 0;
    char byte;
    int fp = open(filename, O_CREAT | O_RDONLY);
    int r;
    do {
        r = read(fp, &byte, 1);
        write(1, &byte, 1);
        if(byte == '\n') newlines++;
    } while(r != 0 && newlines < 10);
}

/* Exercício 10 */

int main(int argc, char** argv) {
    myHead(argv[1]);
    return 0;
}
