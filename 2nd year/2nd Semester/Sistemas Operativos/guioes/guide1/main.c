#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdio.h>
#include <stdlib.h>

#define BUFFERSIZE 1024

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
    for(int i = 0; i < 1024*10; i++) {
        write(fp, buffer, 1024);
    }
}

/* Exercício 2 */
void myCat() {
    char byte;
    int r;
    do {
        r = read(0, &byte, 1);
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
/* Main */
int main(int argc, char **argv) {
    myCatBytesVariable(atoi(argv[1]));
}
