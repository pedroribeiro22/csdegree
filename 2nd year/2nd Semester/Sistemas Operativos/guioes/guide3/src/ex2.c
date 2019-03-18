#include "guide3.h"

int main(int argc, char **argv) {
    if(fork() == 0) {
        execlp("ls", "ls", "-l", NULL);
    }
}
