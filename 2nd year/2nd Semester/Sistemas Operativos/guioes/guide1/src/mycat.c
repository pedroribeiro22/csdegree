#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    char byte;
    int r;
    do {
        r = read(0, &byte, 1);
        write(1, &byte, 1);
    } while(r);
}