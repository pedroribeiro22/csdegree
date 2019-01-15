#include <stdio.h>
#include <stdlib.h>

#define MAX 100
#define OCUP 1
#define LIVRE 0
#define APAGADO (-1)

typedef struct entrada {
  char estado;
  int chave;
  float info;
} THASH[MAX];

/* Hash function */
int hash (int chave, int size) {
  /* forall c, t, 0 <= hash(c,t) < t */
  return (chave % size);
}

/* Função que inicializa a tabela de Hash */
void init (THASH t) {
  int i;
  for(i=0; i<MAX; i++)
    t[i].estado = LIVRE;
}

/* Função de consulta da tabela de Hash */
int consulta (THASH t, int c, float *inf) {
  int p = hash(c, MAX);
  int v = MAX;
  while(t[p].estado != LIVRE && t[p].chave != c && v > 0) {
    p = (p+1) % MAX;
    v--;
  }
  if(v==0) return 1;
  if(t[p].estado != LIVRE) {
    *i = t[p].info;
    return 0;
  }
  return 1;
}

int update (THASh t, int c, float *inf) {
  int p = hash(c, MAX)
}
