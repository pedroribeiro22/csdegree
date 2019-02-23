#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

void head(int fd) {
    int counter = 0;
    int r = 1;
    char c;
    do {
        r = read(fd, &c, 1);
        write(1, &c, 1);
        if(c == '\n') counter++;
    } while(r && counter < 10);
}

int main(int argc, char **argv) {
    int i = 1;
    while(argc > 1) {
        printf("==> %s <==\n", argv[i]);
        int fd = open(argv[i], O_RDONLY);
        head(fd);
        printf("\n");
        i++;
        argc--;
    }
    return 0;
}
