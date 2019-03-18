#include "guide3.h"

int main(int argc, char **argv) {
    execlp("ls", "ls", "-l", NULL);
}
