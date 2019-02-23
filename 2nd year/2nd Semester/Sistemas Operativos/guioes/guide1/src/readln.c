#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

ssize_t readln(int fildes, void* buf, ssize_t nbyte) {
    ssize_t res;
    int i = 0;
    while(i < nbyte && (res += read(fildes, buf + i, 1) > 0)) {
      if(((char *) buf)[i] == '\n') {
        return i;
      }
      i += res;
    }
    return -1;
}
