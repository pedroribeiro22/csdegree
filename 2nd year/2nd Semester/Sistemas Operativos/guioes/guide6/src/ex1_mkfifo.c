#include "guide6.h"

int main() {
    if(mkfifo("fifo", 0666) == -1) {
        perror("fifo");
        return -1;
    }
    return 0;
}