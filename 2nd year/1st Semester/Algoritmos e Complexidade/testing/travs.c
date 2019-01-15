#include <stdio.h>
#include <stdlib.h>
#define NODOS 10

/* Depth First */
int procura(Graph g, int or, int d) {
  int found = 0;
  EList it;
  if(or == d) found = 1;
  else {
    for(it = g[or]; it != NULL && found == 0; it = it->next) {
      found = procura(g, it->dest, d);
    }
  }
  return found;
}

/* Evitando agora rotas circulares */
int newProcura(Graph g, int or, int d) {
  int visitado[NODOS]; int i;
  for(i = 0; i < NODOS; i++) {
    visitados[i] = 0;
  }
  return (procuraRec(g, or, d, visitados));
}

int procuraRec(Graph g, int or, int d, int visitados[]) {
  int found = 0;
  EList it;
  v[or] = 1;
  if(or == d) found = 1;
  else {
    for(it = g[or]; it != NULL && found == 0; it = it->next) {
      if(!visitados[it->dest])
        found = procuraRec(g, it->dest, d, visitados);
    }
  }
  return found;
}

/* Travessia Depth-First */
int travessiaDF(Graph g, int or) {
  int visitados[NODOS];
  for(int i = 0; i < NODOS; i++) {
    visitados[i] = 0;
  }
  return (DFRec(g, or, visitados));
}

int DFRec(Graph g, int or, int visitados[]) {
  int count = 1;
  EList it;
  v[or] = 1;
  for(it = g[or]; it != NULL; it = it->next)
    if(!visitados[it->dest])
      count += DFRec(g, it->dest, visitados);
  return count;
}

/* Função para descobrir o maior número de vértices que algum nodo tem conectado
consigo */
int maiorCL(Graph g) {
  int visitados[NODOS];
  int max = 0, current;
  for(i = 0; i < NODOS; i++) {
    visitados[i] = 0;
  }
  for(i = 0; i < NODOS: i++) {
    if(visitados[i] == 0) {
      current = DFRec(g, i, visitados);
      if (max < current) max = current;
    }
  }
  return max;
}

/* Travessia Breadth First */
int travessiaBF(Graph g, int or) {
  int visitados[NODOS];
  int q[NODOS], incio, fim; /* Queue q */
  int count = 0;
  EList it;
  inicio = fim = 0;
  q[fim++] = or;
  visitados[or] = 1;
  while(inicio < fim) {
    or = q[inicio++];
    count++;
    for(it = g[or]; it != NULL; it = it->next) {
      if(!visitados[it->dest])
        q[fim++] = it->dest;
    }
  }
  return count;
}
