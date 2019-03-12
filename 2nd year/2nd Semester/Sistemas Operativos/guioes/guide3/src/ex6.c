#include "guide3.h"

int newSystem(char *string) {
    int status;
    if(!fork())
        execlp("sh", "sh", "-c", string, NULL);
    wait(&status);
    if(WIFEXITED(status)) {
        return(WEXITSTATUS(status));
    } else {
        return -1;
    }
}

int main(int argc, char **argv) {
    // vamos descobrir o tamanho do comando principal
    // mais as possiveis flags e acomodar tudo num array
    int l = 1;
    for(int i = 1; i < argc; i++)
        l += strlen(argv[i]);
    // alocamos o array acima referido
    char linha[l + argc];
    int j = 0;
    for(int i = 1; i < argc; i++)
        j += sprintf(linha + j, "%s", argv[i]);
        j += sprintf(linha + j, "%s", "  ");
    int result = newSystem(linha);
    if(result == 0) printf("Sucesso\n");
    if(result) printf("Insucesso\n");
    return 0;
}
