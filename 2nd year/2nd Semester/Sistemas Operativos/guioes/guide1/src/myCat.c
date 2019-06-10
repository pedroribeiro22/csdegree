#include "includes.h"

int main(int argc, char **argv) {

    // Carater onde se guarda cada leitura
    char a;

    // Abre o ficheiro e guarda o descritor
    int fd;
    if(fd = open(argv[1], O_RDONLY, 0666)) {
        while(read(fd, &a, 1) > 0)
            write(1, &a, 1);
    }
    else return -1;
}
