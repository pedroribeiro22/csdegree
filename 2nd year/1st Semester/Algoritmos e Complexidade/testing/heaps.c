#include <stdio.h>
#include <stdlib.h>

#define N 100

typedef struct pqueue {
	int quantos;
	int valores[N];
} PQueue;

/* Initialize a PQueue */
PQueue* init() {
	PQueue *new;
	new = malloc(sizeof(struct pqueue));
	new->quantos = 0;
	return new;
}

/* Check if a PQueue is empty */
int empty(PQueue *p) {
	return (p->quantos == 0);
}

void mySwap(int h[], int i, int j) {
	int temp;
	temp = h[i];
	h[i] = h[j];
	h[j] = temp;
}

/* Preserves the maxHeap structure when adding an element */
void myBubbleUp(int h[], int i) {
	// h[0..i[ is a maxHeap
	while(i > 0 && h[i] > h[(i-1)/2])
		mySwap(h, i, (i-1)/2);
	// h[0..i+1[ is a maxHeap
}

/* Adds a number to a PQueue */
/* Returns 0 if add was successful and 1 otherwise */
int add(PQueue *p, int n) {
	int r=0;
	if(p->quantos == N) r=1;
	else {
		p->valores[p->quantos] = n;
		myBubbleUp(p->valores, p->quantos);
		p->quantos++;
	}
	return r;
}


void myBubbleDown(int h[], int n) {
	// h[0..n[ with the exception of h[0] is a maxHeap
	int i=0, f;
	while(2 * i + 1 < n) {
		if(2 * i + 2 < n && h[2 * i + 2] > h[2 * i + 1])
			f = 2 * i + 2;
		else f = 2 * i + 1;
		if(h[i] >= h[f]) break;
		mySwap(h, i, f); i = f;
	}
	// h[0..n[ is a maxHeap
} 

int remove(PQueue *p, int *x) {
	int r=0;
	if(p->quantos == 0) r=1;
	else {
		*x = p->valores[0];
		p->valores[0] = p->valores[p->quantos - 1];
		p->quantos--;
	}
	myBubbleDown(p->valores, p->quantos);
	return r;
}
