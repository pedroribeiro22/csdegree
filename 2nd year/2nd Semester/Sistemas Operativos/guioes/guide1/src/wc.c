#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include "readln.c"

int isLetter(char c) {
    if(c == ' ' || c == '\n' || c == '\0' || c == '\t') return 0;
    return 1;
}

int wordCount(char *string, ssize_t size) {
    int count = 0;
    int inWord = 1;
    for(int i = 0; i < size; i++) {
        if(!inWord && isLetter(string[i])) {
            inWord = 1;
            count++;
        } else if (inWord == 1 && isLetter(string[i]) == 0) inWord = 0;
    }
    return count;
}

int newLineCounter(int fildes) {
    int counter = 0;
    int r = 0;
    char c;
    do {
        r = read(fildes, &c, 1);
        if(c == '\n') counter++;
    } while(r);
    return counter;
}

int main(int argc, char **argv) {
    char buffer[1024];
    int linesSum = 0;
    int wordsSum = 0;
    int totalBytes = 0;
    for(int i = 1; i < argc; i++) {
        int fd = open(argv[i], O_RDONLY);
        int newlines = newLineCounter(fd);
        int words = 0;
        int bytes = 0;
        ssize_t size;
        while((size = readln(fd, buffer, 1024))) {
            words += wordCount(buffer, size);
            bytes += size;
        }
        char string[10];
        sprintf(string, "%d   %d   %d   %s\n", newlines, words, bytes, argv[i]);
        printf("%s\n", string);
        linesSum += newlines;
        wordsSum += words;
        totalBytes += bytes;
    }
    char final[15];
    sprintf(final, "%d  %d  %d   %s\n", linesSum, wordsSum, totalBytes, "total");
    printf("%s\n", final);
    return 0;
}
