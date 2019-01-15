#include <stdio.h>
#include <stdlib.h>
#define N 100
typedef struct bucket {
    int key;
    int value;
    int ocurrencies;
    struct bucket *next;
} Bucket;

typedef Bucket HashTable[N];

//1
/*
3-8-25-9-24-32-37-110-120-31
8-9-25-31-24-32-37-110-120
9-24-25-31-120-32-37-110
24-31-25-110-120-32-37
25-31-32-110-120-37
31-37-32-110-120
32-37-120-110
32-120-37
37-110
110
VAZIO
*/

//2
/*Podemos usar uma tabela de hash com linear probing
(Sempre que exister uma colisão prosseguimos até encontrar um índice disponível*/
/*Função de Hash */
int hash(int key) {
    return (key%N);
}

/* Inicializar a tabela de Hash */
void init(HashTable t) {
    for(int i=0; i < N; i++) {
        t[i] = NULL;
    }
}

/* Introduzir informação na tabela de Hash */
int update(HashTable t, int key, int value) {
    int index = hash(key);
    Bucket runner;
    for(runner = h[i]; runner != NULL && runner.value != value; runner = runner->next);
    if(runner == NULL) {
        runner->next = malloc(sizeof(bucket));
        runner.key = key;
        runner.value = value;
        runner.ocurrencies = 1;
        runner->next = NULL;
    } else {
        runner.ocurrencies++;
    }
    return (runnner.ocurrencies);
}

int repetidos(int v[], int N) {
    HashTable t;
    int r, t = 0;
    init(t);
    for(int i = 0; i < N && !t; i++) {
        r = update(t, v[i], v[i]);
        if(r > 1) t = 1;
    }
    return t;
}

//3
#define NV 100
typedef struct aresta {
    int destino;
    int peso;
    struct aresta *prox;
} *Grafo[NV];

// 1. Existe um único vértice cujo "inDegree é 0"
// 2. Para todos os outros vértices existe um único caminho desse vértice para a raíz
#define BRANCO 1
#define PRETO -1
#define CINZENTO 0

int travessiaBF(Grafo g, int or) {
    int cor[NV], orla[NV];
    int v, i, r=1, inicio, fim;
    struct aresta *pt;
    inicio = fim = 0;
    
    for(i = 0; i < NV; cor[i++] = BRANCO);

    orla[fim++] = or;
    cor[or] = CINZENTO;
    while(inicio != fim) {
        v = orla[inicio++];
        cor[v] = PRETO;
        for(pt = g[i]; pt != NULL; pt = pt->prox) {
            if(cor[pt->dest] == BRANCO) {
                orla[fim++] = pt->dest;
                cor[pt->dest] = CINZENTO;
            } else r=0;
        }
    }
    return r;
}

void getInDegress(Grafo g, int inDegrees[]) {
    struct aresta *pt;
    for(int i = 0; i < NV; inDegrees[i++]=0);
    for(int i = 0; i < N; i++) {
        for(pt = g[i]; pt != NULL; pt = pt->prox) {
            inDegrees[pt->dest]++;
        }
    }
}

int isTree(Grafo g) {
    int inDegrees[NV];
    getInDegrees(g, inDegrees);
    int root, r=-1;
    for(int i = 0; i < NV; i++) {
        int howMany = 0;
        if(inDegrees[i] == 0) {
            howMany++;
            root = i;
        }
    }
    if(howMany == 1) {
        r = travessiaBF(g, i);
    }
    return r;
}

//4
int testHomomorphic(Grafo a, Grafo b, int arr[]) {
    int v, trigger = 1;
    struct aresta *pt, *x;

    for(v = 0; v < NV; v++)
        for(pt = a[v]; pt != NULL; pt = pt->prox) {
            trigger = 0;
            for(x = b[arr[v]]; x != NULL; x = x->prox) {
                if(x->dest == arr[pt->dest]) trigger=1;
            }
        }
    return trigger;
}

int homomorphicRec(Grafo a, Grafo b, int arr[], int i) {
    int v;

    if(i == NV) testHomomorphic(a, b, arr);
    else for(v = 0; v < NV; v++) {
        arr[i] = v;
        if(homomorphicRec(a, b, arr, i+1))
            return 1;
    }
    return 0;
}

