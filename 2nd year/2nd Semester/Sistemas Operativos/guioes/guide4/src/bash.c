#include "../../guide1/src/readln.c"
#include "guide4.h"

char sliceWords(char *string, size_t size, int *wordsCount) {
    char *command = strndup(string, size);
    // make it a decent string
    command[size - 1] = '\0';
    int argc = 5;
    char **argv = malloc(argc * sizeof(char *));
    int i = 0;
    char *token = strtok(command, " ");
    do {
        // se o espaço alocado não chegar
        if(i >= argc)
            argv = realloc(argv, (argc *= 2) * sizeof (char *));
        argv[i++] = token;
        // continua a cortar nos espaços
        token = strtok(NULL, " ");
    } while (token);
    // finish the job
    argv[i] = NULL;
    *wordsCount = i;
    return argv;
}

// Precisamos de ter a capacidade de alterar diretorias
// retorna o sucesso ou insucesso
int cd(char **argv) {
    if(strcmp(argv[0], "cd") != 0) return 0;
    if(!argv[1]) {
        chdir(getenv("HOME"));
        return 1;
    }
    chdir(argv[1]);
    return 1;
}

void redirect(char *file, int t, int append) {
    int fd;
    switch(t) {
        case 0:
            fd = open(file, O_RDONLY);
            break;
        case 1:
            break;
        case 2:
            if(append) fd = open(file, O_CREAT | O_WRONLY | O_APPEND);
            else fd = open(file, O_CREAT | O_WRONLY | O_TRUNC);
            break;
    }
    dup2(fd, t);
    close(fd);
}