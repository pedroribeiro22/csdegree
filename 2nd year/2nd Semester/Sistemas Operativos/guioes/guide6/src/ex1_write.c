#include "guide6.h"

int main(int argc, char **argv) {
    int n;
    int fd = open("fifo", O_WRONLY);
    char buff[1024];
    while((n = read(0, buff, 1024)) > 0)
        write(fd, buff, n);
    return 0;
}