#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

#define BUFFERSIZE 1024
#define TENMiB     1024 * 1024 * 10
/* Exercício 1 */
void populateBuffer(char *string) {
    for(int i = 0; i < 1024; i++) {
        string[i] = 'a';
    }
}

int tenMb(char *nome) {
    char buffer[BUFFERSIZE];
    int fp = open(nome, O_CREAT | O_TRUNC | O_WRONLY, 0777);
    populateBuffer(buffer);
    for(int i = 0; i < (TENMiB / BUFFERSIZE); i++) {
        write(fp, buffer, 1024);
    }
}

/* Exercício 2 */
void myCat() {
    char byte;
    int r;
    do {
        // 0 -> STDIN_FILENO
        r = read(0, &byte, 1);
        // 1 -> SDTOUT_FILENO
        write(1, &byte, 1);
    }
    while(r != 0);
}

/* Exercício 3 */
void myCatBytes(int bytes) {
    char buffer[bytes];
    int r;
    do {
        r = read(0, buffer, bytes);
        write(1, buffer, r);
    }
    while(r != 0);
}

/* Exercício 4 */
void myCatBytesVariable(int bytes) {
    char buffer[bytes];
    int r;
    do {
      r = read(0, buffer, bytes);
      write(1, buffer, r);
    }
    while(r != 0);
}

/* Exercício 5 */
ssize_t readln(int fildes, void *buf, size_t nbyte) {
    ssize_t res;
    for(int i = 0; i < nbyte && res > 0;){
        res += read(fildes, buf + 1, 1);
        if(((char*) buf)[i] == '\n') return i;
        i+=res;
    }
    return -1;
}

/* Exercício 6 */
/* Consideramos um tamanho de buffer aceitável para ler uma linha 1024 */
void nl(char *filename) {
    int lineCounter = 1;
    int n;
    int fp = open(filename, O_CREAT | O_RDONLY);
    char buf[1024];
    while((n = readln(fp, buf, 1024)) > 0) {
        char lineNum[12];
        sprintf(lineNum, "%6d ", lineCounter);
        write(1, lineNum, strlen(lineNum));
        write(1, buf, n);
        if(buf[n-1] == '\n') lineCounter++;
    }
}


/* Main */
int main(int argc, char **argv) {
    nl(argv[1]);
    return 0;
}
