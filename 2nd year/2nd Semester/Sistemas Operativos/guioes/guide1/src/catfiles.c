#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    char c;
    int i = 1;
    int r = 0;
    while(argc > 1) {
        int fd = open(argv[i], O_RDONLY);
        do {
            r = read(fd, &c, 1);
            write(1, &c, 1);
        } while(r);
        i++;
        argc--;
    }
    write(1, "\n", 1);
    return 0;
}
