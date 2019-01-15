#include <stdio.h>
#include <stdlib.h>

//1
//a
//feito em papel - degenera numa lista

//b
// O(log2N) - extractMin


//2
#define MAXV 6
typedef int COLOR;

struct edge {
    int dest;
    struct edge *next;
}

typedef struct edge *Graph[MAXV];

typedef COLOR Colors[MAXV];

// Pode ser uma depth first 
int check_coloring(Graph g, Colors c) {
    struct edge *pt;
    int r = 1;
    for(int i = 0; i < MAXV; i++)
        for(pt = g[i]; pt != NULL; pt = pt->next)
            if(c[i] == c[pt->dest]) r = 0;
    return r;
}

//3
int depthFirst(Graph g, int origin) {
    int visited[MAXV];
    for(int i = 0; i < MAXV; i++) visited[i] = 0;
    return(dfRec(g, origin, visited));
}

int dfRec(Graph g, int origin, int visited[]) {
    int r = 1;
    visited[origin] = 1;
    struct edege *pt;
    for(pt = g[origin]; pt != NULL; pt = pt->prox) {
        if(!visited[pt->dest])
            r += dfRec(g, pt->dest, visited);
    }
    return r;
}

int maiorContinente(Graph g) {
    int maior = 0, current;
    for(int i = 0; i < MAXV; i++) {
        current = depthFirst(g, i);
        if(current > maior) maior = current; 
    }
    return maior;
}