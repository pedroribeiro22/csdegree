#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include "readln.c"

int contains(const char* keyword, char* line, int size)
{
    int i = 0;
    while (i < size) {
        int j = i;
        int k = 0;
        while (j < size && keyword[k] == line[j]) {
            k++;
            j++;
        }
        if (!keyword[k])
            return 1;
        i++;
    }
    return 0;
}

int main(int argc, char const* argv[])
{
    int buffsize = 1;
    char* buf = (char*)malloc(buffsize);

    for (int i = 2; i < argc; i++) {
        int fd = open(argv[i], O_RDONLY);
        int n;
        int offset = 0;
        while ((n = readln(fd, buf + offset, buffsize))) {
            if (buf[n - 1] != '\n') {
                buf = realloc(buf, buffsize * 2);
                offset = buffsize;
                buffsize *= 2;
                continue;
            } else {
                offset = 0;
            }
            if (contains(argv[1], buf, n)) {
                write(1, buf, n);
            }
        }
        close(fd);
    }
    free(buf);
    return 0;
}
