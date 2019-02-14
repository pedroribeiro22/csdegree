#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* int main() { */
/*     char str[30] = "KR1583 77.72 128 P L4891 2 1"; */
/*     const char s[2] = " "; */
/*     char *token; */

/*     token = strtok(str, s); */

/*     while(token != NULL) { */
/*         printf("%s\n", token); */
/*         token = strtok(NULL, s); */
/*     } */
/*     return 0; */
/* } */


int readTransaction() {
    char buffer[30];
    FILE *fp;
    fp = fopen("Vendas_1M.txt", "r");
    if(!fp) return -1;
    while(fgets(buffer, 30, fp)) {
        char *token;
        const char space[2] = " ";
        token = strtok(buffer, space);
        while(token != NULL) {
            printf("%s\n", token);
            token = strtok(NULL, space);
        }
    }
    fclose(fp);
    return 0;
}

int readClients() {
    char buffer[6];
    FILE *fp;
    fp = fopen("Clientes.txt", "r");
    if(!fp) return -1;
    while(fgets(buffer, 6, fp)) {
        printf("%s", buffer);
    }
    fclose(fp);
    return 0;
}

int readProducts() {
    char buffer[7];
    FILE *fp;
    fp = fopen("Produtos.txt", "r");
    if(!fp) return -1;
    while(fgets(buffer, 7, fp)) {
        printf("%s", buffer);
    }
    fclose(fp);
    return 0;

}

int main() {
    readProducts();
    return 0;
}
