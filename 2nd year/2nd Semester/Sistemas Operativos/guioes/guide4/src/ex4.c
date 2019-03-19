#include "guide4.h"

void ex1() {
    int fd = open("/etc/passwd", O_RDONLY);
    int fdSaida = open("saida.txt", O_CREAT | O_TRUNC | O_WRONLY, 00700);
    int fdErros = open("erros.txt", O_CREAT | O_TRUNC | O_WRONLY, 00700);
    dup2(fd, 0);
    close(fd);
    dup2(fdSaida, 1);
    close(fdSaida);
    dup2(fdErros, 2);
    close(fdErros);
}

int main(int argc, char **argv) {

}
