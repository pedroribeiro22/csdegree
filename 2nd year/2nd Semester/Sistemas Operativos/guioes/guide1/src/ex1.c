#include "includes.h"

// 10 MB
#define TENMB (10 * 1024 * 1024)


int main(int argc, char **argv) {

    // Carater a escrever
    char toWrite = 'a';

    // A função recebe em argv[1] o nome que o ficheiro terá
    char *nome = argv[1];

    // Criar o ficheiro
    int fd = open(nome, O_CREAT | O_TRUNC | O_WRONLY, 0666);

    // Preencher ficheiro
    for(int i = 0; i < TENMB; i++)
        write(fd, &toWrite, 1);

    return 0;
}
