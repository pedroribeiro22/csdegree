#include <stdio.h>
#include <stdlib.h>

//1
// Pretty basic stuff

//2

#define MaxV 6

struct edge {
    int dest;
    struct edge *next;
}

typedef struct edge *Grafo[MaxV];

int succN(Grafo g, int v, int N) {
    int visited[MaxV];
    int dist[MaxV];
    int r;
    struct edge *pt;
    // orla related
    int orla[MaxV];
    int inicio, fim;
    inicio = fim = 0;
    for(int i = 0; i < MaxV; i++) {
        visited[i] = 0;
        dist[i] = 0;
    }
    orla[fim++] = v;
    visited[v] = 1;
    while(inicio < fim) {
        v = orla[inicio++];
        for(pt = g[v]; pt != NULL; pt = pt->dest)
            if(!visited[pt->dest]) {
                dist[x->dest] = 1 = dist[v];
                orla[inicio++] = pt->dest;
                visited[pt->dest] = 1;
            }
    }
    int counter = 0;
    for(int i = 0; i < MaxV; i++) {
        if(dist[i] <= v) counter++;
    }
    return counter;
}

//3
#define BAL 0 // Balanceada
#define ESQ -1 // Esquerda mais pesada
#define DIR 1 // Direita mais pesada

typedef struct avlNode *AVL;

struct avlNode {
    int bal;
    int valor;
    struct avlNode *esq, *dir;
}

int altura(AVL a) {
    int height = 0;
    while(a->left || a->right) {
        if(a->bal == ESQ) a=a->esq;
        else a=a->right;
        height++;
    }
    return height;
}

// PARTE B
int custo[MaxV];    // custo de cada vertice
int posicao[MaxV];  // posicao na heap
int heap[MaxV];
int heapsize;       // numero de elementos na heap


// Nao percebi honestamente

//2
