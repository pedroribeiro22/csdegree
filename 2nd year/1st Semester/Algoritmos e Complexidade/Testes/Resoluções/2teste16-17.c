#include <stdio.h>
#include <stdlib.h>

//1
/*
(0)NULL- (1)key = 40     (2)-NULL-(3)NULL-(4)NULL-(5)NULL-(6)NULL-(7)NULL-(8)NULL-(9)NULL-(10)NULL-(11)NULL-(12)NULL
            probe = 0
            status = USED

(0)NULL- (1)key = 40     -(2)key = 80  -  (3)NULL-(4)NULL-(5)NULL-(6)NULL-(7)NULL-(8)NULL-(9)NULL-(10)NULL-(11)NULL-(12)NULL
            probe = 0        probe = 0
            status = USED    status = USED

(0)NULL- (1)key = 40     -(2)key = 80  -  (3)NULL-(4)NULL-(5)NULL-(6)NULL-(7)NULL-(8)key = 60 -    (9)NULL-(10)NULL-(11)NULL-(12)NULL
            probe = 0        probe = 0                                               probe = 0         
            status = USED    status = USED                                           status = USED

(0)key = 260 - (1)key = 40     -(2)key = 80  -  (3)NULL-(4)NULL-(5)NULL-(6)NULL-(7)NULL-(8)key = 60 -    (9)NULL-(10)NULL-(11)NULL-(12)NULL
   probe = 0      probe = 0        probe = 0                                               probe = 0         
   status = USED  status = USED    status = USED                                           status = USED

(0)key = 260 - (1)key = 40     -(2)key = 80  -  (3)key = 54 -    (4)NULL-(5)NULL-(6)NULL-(7)NULL-(8)key = 60 -    (9)NULL-(10)NULL-(11)NULL-(12)NULL
   probe = 0      probe = 0        probe = 0       probe = 1                                        probe = 0         
   status = USED  status = USED    status = USED   status = USED                                        status = USED

(0)key = 260 - (1)key = 40     -(2)key = 80  -  (3)key = 54 -    (4)key = 65-     (5)NULL-(6)NULL-(7)NULL-(8)key = 60 -    (9)NULL-(10)NULL-(11)NULL-(12)NULL
   probe = 0      probe = 0        probe = 0       probe = 1        probe = 4                                probe = 0         
   status = USED  status = USED    status = USED   status = USED    status = USED                                    status = USED

(0)key = 260 - (1)key = 40     -(2)key = 80  -  (3)key = 54 -    (4)key = 65-     (5)NULL-(6)NULL-(7)NULL-(8)key = 60 -    (9)NULL-(10)key = 140-    (11)NULL-(12)NULL
   probe = 0      probe = 0        probe = 0       probe = 1        probe = 4                                probe = 0                 probe = 0
   status = USED  status = USED    status = USED   status = USED    status = USED                            status = USED             status = USED
*/

//2
#define LEFT 1
#define BAL 0
#define RIGHT -1

typedef struct avl {
    int value;
    int bal;
    struct avl *left, *right;
} *AVL;

//a
AVL maisProfundo(AVL a) {
    while(a->left || a->right) {
        if(a->bal == LEFT) a = a->left,
        else a = a->right;
    }
    return a;
}

//b
/* retorna o nodo 29*/
/* FAZER O DESENHO DA ARVORE COM OS RESPETIVOS BALANCOS PARA
JUSTIFICAR */

//3
//a
#define N 100
/* Grafo não pesado */
typedef struct edge {
    int dest;
    struct edge *next;
} *AdjList;
typedef AdjList Graph[N];

#define WHITE 0
#define RED 1
#define GREEN 2

/* TRAVESSIA BREADTH FIRST */
int bicolor(Graph g, int color[]) {
    Queue q;
    struct edge *pt, *ant;
    int r = 1;
    init_queue(q);
    for(v = 0; v < N; v++) color[v] = WHITE;
    enqueue(q, 0);
    color[0] = RED;
    while(!is_empty(q) && ---) {
        v = dequeue(q);
        ant = g[v];
        for(pt = g[v]; pt != NULL; pt = pt->next) {
            if(pt->next == WHITE) {
                if(ant == RED) cor[pt->dest] = GREEN;
                else cor[pt->dest] = RED;
                ant = pt; 
                enqueue(q, pt->dest);
            } else r = 0;
        }
    }
    return r;
}

int bicolor(Graph g, int color[]) {
    int orla[N];
    int incio, fim;
    int v, r = 1;
    struct edge *pt;
    for(int i = 0; i < N; i++) color[i] = WHITE;
    color[0] = RED;
    inicio = fim = 0;
    orla[fim++] = g[i];
    while(inicio < fim) {
        v = orla[inicio++];
        for(pt = g[v]; pt != NULL; pt = pt -> prox) {
            if(cor[pt->dest] == WHITE) {
                if(cor[v] == RED) cor[pt->dest] = GREEN;
                if(cor[v] == GREEN) cor[pt->dest] = RED;
                orla[fim++] = pt->dest;
            } else {
                if(color[v] == color[pt->dest]) {r = 0; break;}
            }
        }

    }
    return r;
}
//b
/* pior caso = O(N)
   melhor caso = O(log2(N))
*/

//PARTE B
//1
#define N /* Número de vértices do grafo */
/* Podemos gerar cores e coloca-las num array */
/* Algoritmos não deterministico */
int k_color(Grafo g, int k) {
    int exp[N];
    return(colorRec(g, k, exp, 0));
}

int colorRec(Grafo g, int k, int cor[], int i) {
    
    if(i == N) return(colorOK(g, cor));
    else for(int x = 0; x < k; i++) {
        cor[i] = x;
        if(colorRec(g, k, cor, i+1))
            return 1;
    }
    return 0;
}

int colorOK(Grafo g, int cor[]) {
    int r=1;
    struct aresta *pt;
    for(int i = 0; i < N; i++)
        for(pt = g[i]; pt != NULL; pt = pt->next)
            if(cor[pt->dest] == cor[pt])
                r = 0;
    return r;
}