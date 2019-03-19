#include "guide4.h"

void ex3() {
    int fd = open("/etc/passwd", O_RDONLY);
    int fdSaida = open("saida.txt", O_CREAT | O_TRUNC | O_WRONLY, 00700);
    int fdErros = open("erros.txt", O_CREAT | O_TRUNC | O_WRONLY, 00700);
    dup2(fd, 0);
    close(fd);
    dup2(fdSaida, 1);
    close(fdSaida);
    dup2(fdErros, 2);
    close(fdErros);
    execlp("wc", "wc", NULL);
}

int main(int argc, char **argv) {
    ex3();
    return 0;
}
