#include "includes.h"

int main(int argc, char **argv) {

    // Buffer de leitura (10 carateres de cada vez)
    char *buffer[10];

    // Abertura do ficheiro
    int fd;
    size_t r;
    if((fd = open(argv[1], O_RDONLY, 0666))) {
        while((r = read(fd, buffer, 10)) > 0)
            write(1, buffer, r);
    }
    else return -1;
}
