#include "guide6.h"

int main(int argc, char **argv) {

    printf("Starting server.......\n");
    mkfifo("pip", 0666);
    printf("Forking.....\n");
    if(fork() == 0) {
        int input = open("pip", O_RDONLY);
        int log = open("log.txt", O_CREAT | O_WRONLY, 0666);

        while(1) {
            char buff[1024];
            int n = read(input, buff, 1024);
            if(n > 0) write(log, buff, n);
        }
    } else wait(NULL);
    return 0;
}