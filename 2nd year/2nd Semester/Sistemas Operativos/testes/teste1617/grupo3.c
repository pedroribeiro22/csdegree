#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>

int main(int argc, char **argv) {

     int n = atoi(argv[1]);

     int pipesFiltro[n][2];
     int pipesExiste[n][2];

     int r = 0;
     int counter = 0;

     for(int i = 0; i < n; i++) pipe(pipesFiltro[i]);

     while((size = readln(0, buf, 4096)) > 0) {
        write(pipesFiltro[r][1], buf, size);
        r = (r + 1) % n;
     }

     for(int i = 0; i < n; i++) {
         pipe(pipesExiste[i]);
         if(fork() == 0) {
             close(pipesFiltro[i][1]);
             close(pipesExiste[i][0]);
             dup2(pipesFiltro[i][0], 0);
             dup2(pipesExiste[i][1], 1);
             execlp("filtro", "filtro", NULL);
         }
     }

     for(int i = 0; i < n; i++) {
         if(fork() == 0) {
             close(pipesExiste[i][1]);
             dup2(pipesExiste[i][0], 0);
             execlp("existe", "existe", NULL);
         }
         else {
             wait(&status);
             if(WEXITSTATUS(status) == 1) counter++;
         }
     }
     return counter;
}
