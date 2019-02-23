#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include "readln.c"

int main(int argc, char **argv) {
  if(argc < 1) {
    printf("Please, provide a file.\n");
    return -1;
  }
  int count = 1;
  int n = 0;
  int fd = open(argv[1], O_CREAT | O_RDONLY);
  char buffer[1024];
  while((n = readln(fd, buffer, 1024)) > 0) {
    if(n != 1) {
      char line[12];
      sprintf(line, "%6d ", count);
      write(1, line, strlen(line));
      if(buffer[n - 1] == '\n') {
        count++;
      }
    }
    write(1, buffer, n);
  }
}
