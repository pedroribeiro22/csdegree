#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>

void povoaMatriz(void **matriz, int rows, int columns) {

    int **myMatriz = (int **) matriz;
    for(int i = 0; i < rows; i++)
        for(int j = 0; j < columns; j++) {
            srand(time(NULL));
            myMatriz[i][j] = rand() % 10;
        }
}

int main(int argc, char **argv) {

    int matriz[20][50];
    povoaMatriz(matriz, 20, 50);
}
