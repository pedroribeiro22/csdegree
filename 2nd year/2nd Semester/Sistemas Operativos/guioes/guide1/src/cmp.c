#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    if(argc != 3) {
        printf("I only accept 2 files at a time bud!\n");
        return 1;
    }
    /* temos que abrir os dois ficheiros */
    int fildes1 = open(argv[1], O_RDONLY);
    int fildes2 = open(argv[2], O_RDONLY);
    char c1, c2; /*  para comparar 1 a 1 */
    int r1, r2, byteCounter = 0, lineCounter = 0;
    /*  enquanto ambos os ficheiros tiverem alguma coisa para ler: */
    while((r1 = read(fildes1, &c1, 1)) > 0 && (r2 = read(fildes2, &c2, 1)) > 0) {
        if(c1 != c2) break;
        if(c1 == '\n') {
            lineCounter++;
            byteCounter = 0;
        }
        byteCounter++;
    }
    if(c1 == c2) printf("Files are equal.\n");
    else if (r1 < 1) printf("File %s is over.\n", argv[1]);
    else if (r2 < 1) printf("File %s is over.\n", argv[2]);
    else printf("%s %s differ: char %d, line %d\n", argv[1], argv[2], byteCounter, lineCounter);
    return 0;
}
