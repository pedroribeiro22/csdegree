#include "guide1.h"

static void populateBuffer(char *buffer, int nbyte) {
    for(int i = 0; i < nbyte; i++)
        buffer[i] = 'a';
}

void tenmb(int fp) { 
    char buffer[1024];
    populateBuffer(buffer, 1024);
    for(int i = 0; i < TENMB; i++)
        write(fp, buffer, 1024);
}

void mycat() {
    char byte;
    int r;
    do {
        r = read(0, &byte, 1);
        write(1, &byte, 1);
    } while(r);
}

void catnbytes(ssize_t nbytes) {
    char buffer[nbytes];
    int r;
    do {
        r = read(0, buffer, nbytes);
        write(1, buffer, nbytes);
    } while(r);
}

ssize_t readln(int fildes, void *buf, size_t nbyte) {
    ssize_t size = 0;
    char c;
    char *buff = (char *) buf;
    while(size < nbyte && read(fildes, &c, 1) == 1) {
        buff[size++] = c;
        if(c == '\n')
            return size;
    }
    return size;
}

void ln(int fd) {
    int line = 1;
    int n = 0;
    char buf[1024];
    while((n = readln(fd, buf, 1024)) > 0) {
        if(n != 1) {
            char lineNum[12];
            sprintf(lineNum, "%6d ", line);
            write(1, lineNum, strlen(lineNum));
            if(buf[n - 1] == '\n') line++;
        }
        write(1, buf, n);
    }
}

void catfile(int fp) {
    char c;
    int i = 1;
    int r = 0;
    do {
        r = read(fp, &c, 1);
        write(1, &c, 1);
    } while(r);
    write(1, "\n", 1);
}

void head(int fd) {
    int counter = 0;
    int r = 1;
    char c;
    do {
        r = read(fd, &c, 1);
        write(1, &c, 1);
        if(c == '\n') counter++;
    } while(r && counter < 10);
    write(1, "\n", 1);
}

int contains(const char *keyword, char *line, int size) {
    int i = 0;
    while(i < size) {
        int j = i;
        int k = 0;
        while(j < size && keyword[k] == line[j]) {
            k++;
            j++;
        }
        if(!keyword[k]) return 1;
        i++;
    }
    return 0;
}

void grep(int fp) {
    int buffsize = 1;
    int n;
    int offset = 0;
    char *buf = (char *) malloc(buffsize);
    while(n = readln(fp, buf + offset, buffsize)) {
        if(buf[n - 1] != '\n') {
            buf = realloc(buf, buffsize * 2);
            offset =  buffsize;
            buffsize *= 2;
            continue;
        } else offset = 0;
    }
    close(fp);
    free(buf);
}


int main(int argc, char **argv) {
    return 0;
}