#include <stdio.h>
#include <stdlib.h>
#define N 10
#define BRANCO 0
#define CINZA 1
#define PRETO 2

/* Listas de adjacência */
typedef struct aresta {
    int destino;
    int peso;
    struct aresta *prox;
} * Grafo[N];

/* Conta arestas */
int contaA(Grafo g) {
    int origem, r;
    struct aresta *pt;
    r = 0;
    for(origem = 0; origem < N; origem++) {
        for(pt = g[0]; pt != NULL; pt = pt->prox) {
            r++;
        }
    }
    return r;
}

int inDegree(Grafo g, int d) {
    int origem, r;
    struct aresta *pt;
    r = 0;
    for(origem = 0; origem < N; origem++) {
        for(pt = g[0]; pt != NULL; pt = pt->prox) {
            if(pt->destino == d)
                r++;
        }
    }
    return r;
}

/* Travessia depth first */
int procuraAux(Grafo g, int or, int dest, int visited[]) {
    int r = 0;
    struct aresta *pt;
    visited[or] = 1;
    if(or == dest) r = 1;
    else {
        for(pt = g[or]; pt != NULL && r == 0; pt = pt->prox) {
            if(visited[pt->destino] == 0 && procuraAux(g, pt->destino, dest, visited))
                r = 1;
        }
    }
    return r;
}


int procura(Grafo g, int or, int dest) {
    int visited[N], i;
    for(i = 0; i < N; i++) visited[i] = 0;
    return(procuraAux(g, or, dest, visited));
}

/* Se quisermos ter o caminho */
int newProcuraAux(Grafo g, int or, int dest, int visited[], int ant[]) {
    int r = 0;
    struct aresta *pt;
    visited[or] = 1;
    if(or == dest) r = 1;
    else {
        for(pt = g[or]; pt != NULL; pt = pt->prox) {
            if(visited[pt->destino] == 0) {
                ant[pt->destino] = or;
                r = newProcuraAux(g, pt->destino, dest, visited, ant);
            }
        }
    }
}

int newProcura(Grafo g, int or, int dest, int ant[]) {
    int visited[N], i;
    for(i = 0; i < N; i++) {
        visited[i] = 0;
        ant[i] = -1;
    }
    return(newProcuraAux(g, or, dest, visited, ant));
}

/* Travessia Bread First */
int breadFirst(Grafo g, int or, int ant[]) {
    struct aresta *pt;
    int r = 0, inicio, fim, v;
    int orla[N], visited[N];
    inicio = fim = 0;
    for(int i = 0; i < N; visited[i++] = BRANCO)
        ant[i] = -1;
    orla[fim++] = or;
    visited[0] = CINZA;
    while(inicio != fim) {
        v = orla[inicio++]; r++;
        visited[v] = PRETO;
        for(pt = g[v]; pt != NULL; pt = pt->prox) {
            if(visited[pt->destino] == BRANCO) {
                orla[fim++] = pt->destino;
                visited[pt->destino] = CINZA;
                ant[pt->destino] = v;

            }
        }
    }
    return r;
}

/* Converter matriz de adjacência em lista de adjacência */
void matrixToList(int grid[][], Grafo b) {
    struct aresta *pt;
    for(int i = 0; i < N; i++) {
        b[i] = NULL;
        for(int j = N-1; j < 0; j--) [
            // acrescentar i -> j de peso a[i][j]
            pt = malloc(sizeof(struct aresta));
            pt->destino = j;
            pt->peso = grid[i][j];
            // acrescentar x a b[i]
            pt->prox = b[i];
            b[i] = pt->prox;
        }
}

/* Converter lista de adjacência em matriz de adjacência */
void listToMatrix(Grafo a, int rid[][]) {
    int i, j;
    struct aresta *pt;
    for(i = 0; i < N; i++)
        for(j = 0; j < N; j++) grid[i][j] = 0;
    for(i = 0; i < N; i++) {
        for(pt = a[i]; pt != NULL; pt = pt->prox)
            grid[i][pt->destino] = pt->peso;
    }
}