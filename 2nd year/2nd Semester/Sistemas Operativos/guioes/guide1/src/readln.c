#include "includes.h"


ssize_t readln(int fd, void *buf, size_t nbyte) {

    // Esta função lê 1 carater de cada vez
    // Contador de bytes lidos
    size_t counter = 0;

    // Carater de leitura
    char a;

    // Cast do buf para `char*`
    char *buffer = (char *) buf;

    while(counter < nbyte && read(fd, &a, 1)) {

        buffer[counter++] = a;
        // Caso tenha acabado a linha
        if(a == '\n') return counter;
    }
    return counter;
}

