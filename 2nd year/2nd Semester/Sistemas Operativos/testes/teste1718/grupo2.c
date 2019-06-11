#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

// Número máximo de processos
#define MAX_PIDS 10

// Array para guardar o PID de cada processo
int pids[MAX_PIDS];

// Função que faz tentativa
int fazTentativa(int argc, char **argv) {

    int ret = 0;
    int status;

    // Lançar os processos
    for(int i = 0; i < argc; i++)
        if((pids[i] = fork()) == 0) {
            execlp(argv[i], argv[i], NULL);
            _exit(0);
        }

    // Esperar pelos processos
    for(int i = 0; i < argc; i++) {
        wait(&status);
        if(WEXITSTATUS(status) == 0) {
            for(int p = 0; p < argc; p++) {
                if(waitpid(pids[p], NULL, WNOHANG) == 0)
                    kill(pids[i], SIGTERM);
            }
            return 0;
        }
        else ret += WEXITSTATUS(status);
    }
    return ret;
}

// Função que trata do sinal de fim de tempo
void handleTime(int sig) {
    for(int i = 0; i < MAX_PIDS; i++)
        if(waitpid(pids[i], NULL, WNOHANG) == 0)
            kill(pids[i], SIGTERM);
}

int main(int argc, char **argv) {
    int flag;
    flag = fazTentativa(argc, argv);
    if(flag == 0) return 0;
    else {
        while(flag) {
            alarm(flag);
            flag = fazTentativa(argc, argv);
        }
    }
    return 0;
}
