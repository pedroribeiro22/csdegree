#include <stdio.h>
#include <stdlib.h>

// 1
// Nao era objetivo de avaliacao no meu segundo teste

//2
typedef struct minheap {
    int size;
    int used;
    int values[];
} *MinHeap;

//a
// Temos uma minheap com a seguinte estrutura:
// 4 - 10 - 21 - 45 - 13 - 25 - 22 - 60 - 100 - 20

// Consideremos que vamos inserir o elemento 6
// Ficamos com:
// 4 - 10 - 21 - 45 - 13 - 25 - 22 - 60 - 100 - 20 - 6
// Fazendo bubbleUp (fastForwarding)
// 4 - 6 - 21 - 45 - 10 - 25 - 22 - 60 - 100 - 20 - 13

//b
int minHeapOK(MinHeap h) {
    int i=h->size - 1;
    int r=1;
    int father;
    for(; i >= 0; i--) {
        father = (i-1)/2;
        if(h->values[father] > h->values[i]) r=0;
    }
    return r;
}

//3
#define MaxV 6
typedef struct edge {
    int dest;
    int cost;
    struct edge *next;
} *Edge, *Graph[MaxV];

int colorOK(Graph g, int color[]) {
    int r=1;
    struct edge *pt;
    for(int i = 0; i < MaxV; i++)
        for(pt = g[i]; pt != NULL; pt = pt->next)
            if(color[pt->dest] == color[i]) r=0;
    return r;
}

//4
typedef struct entry {
    char key[10];
    void *info;
    struct entry *next;
} *Entry;

typedef struct hashT {
    int hashsize;
    Entry table[];
} *HashTable;

HashTable newTable(int hashsize) {
    struct hashT *t;
    struct entry *new;
    t->hashsize = hashsize;
    for(int i = 0; i < hashsize; i++) {
        new = malloc(sizeof(struct edge));
        t->table[i] = new;
    }
    return &t;
}

//5
// Nao e objetivo de avaliacao neste teste


// PARTE B
//1
int excentricity(Graph g, int v) {
    int orla[MaxV];
    int dist[MaxV];
    int visitados[MaxV];
    int inicio, fim;
    struct edge *pt;
    inicio = fim = 0;
    for(int i = 0; i < MaxV; i++) {
        visitados[i] = 0;
        dist[i] = 0;
    }
    visitados[v] = 1;
    orla[fim++] = v;
    while(inicio < fim) {
        v = orla[inicio++];
        for(pt = g[v]; pt != NULL; pt = pt->next) {
            if(!visitados[pt->dest]) {
                if(dist[pt->dest] == 0) {
                    dist[pt->dest] = dist[v] = 1;
                }
                orla[fim++] = pt->dest;
                visitados[pt->dest] = 1;
            }
        }
    }

    // checkar no array qual a maior excentricidade que temos
    int maior = 0;
    for(int i = 0; i < MaxV; i++) {
        if(dist[i] > maior) maior = dist[i];
    return maior;
}

//2
//a
// Propriedade arbitraria. p.ex: soma dos elementos ser 10
int teste(int v[], int N, int x[]) {
    int sum = 0;
    for(int i = 0; i < N; i++) {
        sum += v[i] * x[i];
    }
    return (sum == 10);
}

int forall(int v[], int N, int x[]) {
    int r;
    r = forallRec(v, N, x, 0);
    return r;
}

int forallRec(int v[], int N, int x[], int i) {
    if(i == N) return(teste(v, N, x));
    else
    {
        for(int v = 0; v <= 1; v++) {
            x[i] = v;
            if(forallRec(v, N, x, i+1))
                return 1;
        }
    }
    return 0;
}

//b
// o pior caso seria nao conseguir gerar um conjunto valido
// a complexidade seria: TETA(2^N);

//c
// feito acima