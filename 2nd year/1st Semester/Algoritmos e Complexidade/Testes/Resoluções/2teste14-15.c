#include <stdio.h>
#include <stdlib.h>

//PARTE A
//1
typedef struct data {
    int dados;
} Data;

typedef struct node {
    int balance;
    char key[11];
    Data info;
    struct node *left, *right;
} Node;

typedef Node *Dictionary;

//a
// fazer no caderno

//b
int allCopies(Dicitionary dic, char key[11]) {
    // sabemos que a árvore está ordernada
    // segundo as keys, portanto e uma questao de ir
    // fazendo strcmp;
    int counter = 0;
    while(dic && (strcmp(key, dic->key))) {
        if(strcmp(key, dic->key) > 0) dic = dic->esq;
        else dic = dic->dir;
    }
    if(!dic) return counter;
    else {
        counter++;
        while((strcmp((dic->esq)->key, key)) == 0) {
            dic = dic-esq;
            counter++;
        }
    }
    return counter;
}
// O tempo de execucao disto e O(log2N), no numero de elementos
// da arvore

//2
// orla : 0
// antecessores: -1, -2, -2, -2, -2, -2, -2
// pesos: 0, 0, 0, 0, 0, 0, 0

//orla : 1 - 3 - 4
// antecessores: -1, -2, -2, 0, -2, -2, -2
// pesos: 0, 0, 0, 2, 0, 0, 0

//orla : 1 - 3 - 4
// antecessores: -1, -2, -2, 0, -2, -2, -2
// pesos: 0, 0, 0, 2, 0, 0, 0

//3
int quantosDepois(Grafo g, int v, int dist) {
    int pais[N], pesos[N];
    int counter = 0;
    dijkstraSP(g, v, pais, pesos);
    for(int i = 0; i < N; i++) {
        if(pesos[i] > dist) counter++;
    }
    return counter;
}

//Exercicio 4
typedef struct aresta {
    int destino;
    int peso;
    struct aresta *prox;
} *Grafo[N];

//podemos utilizar um array com 0 e 1 para decidir quais os verticces
// que pertencem a C
// temos que gerar valoracoes para saber quais desses k vertices
// pertencem a C
/* Um algoritmos nao deterministico que resolve este problema   
é um algoritmo que gere todas as valoracoes possiveis no que diz
respeito a quais os vertices que pertencem ao conjunto C */

int kSet(Grafo g, int k) {
    int exp[N];
    return(kSetRec(g, k, exp, 0));
}

int kSetRec(Grafo g, int k, int exp[], int i) {
    
    if(i == N) return(kSetTest(g, k, exp));
    else {
        for(int v = 0; v < k; i++) {
            exp[i] = v;
            if(kSetRec(g, k, exp, i+1))
                return 1;
        }
    }
    return 0;
}

int kSetTest(Grafo g, int k, int exp[]) {
    int r=1;
    int int1, int2;
    for(int i = 0; i < k && r = 1; i++) {
        if(exp[i]) {
            int1 = (g[i] == NULL)
            int2 = testDest(g, i);
        }
        r = (int1 || int2);
    }
    return r;
}

int testDest(Grafo g, int v) {
    int r=0;
    struct aresta *pt;
    for(int i = 0; i < N; i++)
        for(pt = g[i]; pt != NULL; pt = pt->prox)
            if(pt->dest == v) r = 1;
    return r;
}