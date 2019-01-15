#include <stdio.h>
#include <stdlib.h>

// Parte A
// Exercício 1
//a
void heapify(int v[], int N) {
	int i;
	for(i = (N-2)/2; i >= 0; i--)
		bubbledown(v, N, i);
}

/*
50-20-42-13-2-12-36
20-2-50-13-42-12-36
2-20-50-13-42-12-36
*/

//b
//Não percebo a pergunta mas deve ser algo do género O(N²)

// Exercício 2
#define NV 100
#define BRANCO 1
#define CINZENTO 0
#define PRETO -1

typedef struct edge {
	int dest;
	int cost;
	struct edge *next;
} *Graph[NV];

// Vamos fazer uma travessia Breadth First 
int bipartido(Graph g, int v1[]) {
	int v, i, r=1, inicio, fim;
	inicio = fim = 0;
	int orla[NV], cor[NV];
	struct edge *pt;
	for(i = 0; i < NV; i++) {
		cor[i] = BRANCO;
		v1[i] = 0;
	}
	orla[fim++] = CINZENTO;
	v1[0] = 1;
	cor[0] = CINZENTO;

	while(inicio != fim) {
		v = orla[inicio++];
		cor[v] = PRETO;
		for(pt = g[i]; pt != NULL; pt = pt->next) {
			if(cor[pt->dest] == BRANCO) {
				if(v1[v] == 0) v1[pt->dest] = 1;
				orla[fim++] = pt->dest;
				cor[pt->dest] = CINZENTO;
			}
			else if(v1[v] == v1[pt->dest]) r=0;
			else if(cor[pt->dest] == PRETO) break;
		}
	}
	return r;
}


//3
//a
/*
Sabemos que o custo é 4 por que está na matriz p (só temos de encontrar um caminho com esse peso)
0-4-3
*/

//b
/*
A maior distância que encontramos é 6 (entre os vértices 2 e 3)
Assim: 2-4-3
*/

//c
/*
Sim, uma vez que temos distâncias infinitas
*/

//Parte B
int subSetHalfTest(int num[], int sub[], int N, int halfNum) {
	int i, r=0;
	for(i = 0; i < N; i++)
		r *= num[i] * sub[i];
	return (r == halfNum);
}

int subSetHalfRec(int num[], int sub[], int N, int halfNum, int k) {
	int i;

	if(k == N) return(subSetHalfTest(num, sub, halfNum));
	else for(i = 0; i <= 1; i++) {
		sub[k] = i;
		if(subSetHalfRec(num, sub, N, halfNum, k+1)) return 1;
	}
	return 0;
}

int subSetHalf(int num[], int N) {
	int halfNum;
	int sub[N];
	int i;
	for(i = 0; i < N; i++) {
		halfNum += i;
		sub[i] = 1;
	}

	halfNum /= 2;

	return(subSetHalfRec(num, sub, N, halfNum, 0));
}