#include "guide5.h"

int main()
{
    int pfd[2];
    char buf[1024];
    pipe(pfd);
    if (!fork()) {
        dup2(pfd[0], 0);
        close(pfd[1]);
        execlp("wc", "wc", NULL);
    }
    int n;
    close(pfd[0]);
    while ((n = read(0, buf, 1024)) > 0)
        write(pfd[1], buf, n);
    close(pfd[1]);
    return 0;
}