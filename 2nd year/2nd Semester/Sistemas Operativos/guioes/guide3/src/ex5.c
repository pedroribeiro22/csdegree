#include "guide3.h"

int main(int argc, char **argv) {
    for(int i = 1; i < argc; i++) {
        if(!fork())
            execlp(argv[i], argv[i], NULL);
    }
}
