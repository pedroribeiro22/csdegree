#include "includes.h"
#include "readln.c"
#include <stdio.h>

int main(int argc, char **argv) {

    // Buffer de leitura
    char buffer[1024];
    char printer[1024];
    int fd;
    if(argc == 1) fd = 0;
    else fd = open(argv[1], O_RDONLY, 0666);

    int lineCounter = 1;
    size_t size;

    while((size = readln(fd, buffer, 1024))) {
        sprintf(printer, "%d %s", lineCounter, buffer);
        write(1, printer, size + 2);
        lineCounter++;
    }
    return 0;
}
