#include "guide4.h"

int main(int argc, char **argv) {
    int fd;
    for(int i = 1; argv[i]; i++)
        if(argv[i][0] == '-') {
            switch(argv[i][1]) {
                case 'i':
                    fd = open(argv[i++], O_RDONLY);
                    dup2(fd, 0);
                    close(fd);
                    break;
                case 'o':
                    fd = open(argv[i++], O_CREAT | O_WRONLY | O_TRUNC);
                    dup2(fd, 1);
                    close(fd);
                    break;
            }
        } else {
            execvp(argv[i], argv + i);
        }
    return 0;
}