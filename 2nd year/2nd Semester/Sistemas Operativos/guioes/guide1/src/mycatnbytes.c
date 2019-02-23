#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    printf("How many bytes each time do you want me to read?\n");
    int bytes;
    scanf("%d", &bytes);
    char buffer[bytes];
    int r;
    do {
        r = read(0, buffer, bytes);
        write(1, buffer, bytes);
    } while(r);
}