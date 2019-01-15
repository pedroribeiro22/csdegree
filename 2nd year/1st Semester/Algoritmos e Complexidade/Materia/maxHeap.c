#include <stdio.h>
#include <stdlib.h>

/* Tamanho máximo de uma maxHeap */
#define N 10

/* A maxHeap tem de estar sempre balanceada */
typedef struct pqueue {
  int quantos;
  int valores[N];
} PQueue;

/* Inicializar a estrutura */
PQueue* init() {
  PQueue *new;
  new = malloc(sizeof(struct pqueue));
  new->quantos = 0;
  return new;
}

void swap(int h[], int i, int j) {
  int temp;
  temp = h[i];
  h[i] = h[j];
  h[j] = temp;
}
/* Verificar se uma maxHeap é vazia */
int vazia (PQueue *p) {
  return (p->quantos == 0);
}

void bubbleUp (int h[], int n) {
  /* h[0..i[ é uma maxHeap */
  int i = n;
  while(i>0 && h[i]>h[(i-1)/2]) {
    swap(h, i, (i-1)/2);
    i = (i-1)/2;
  }
  /* h[0..i+1[ é uma maxHeap */
}

/* Acrescentar um elemento à maxHeap */
int acrescenta (PQueue *p, int x) {
  int r = 0;
  if (p->quantos == N) r=1;
  else {
    p->valores[p->quantos] = x;
    bubbleUp(p->valores, p->quantos);
    p->quantos++;
  }
  return r;
}

void print(PQueue *p) {
  int i = 0;
  while(i < p->quantos) {
    printf("%d\n", p->valores);
    i++;
  }
}

int main() {
  PQueue *new;
  new = init();
  acrescenta(new, 10);
  acrescenta(new, 20);
  acrescenta(new, 30);
  print(new);
  return 0;
}
