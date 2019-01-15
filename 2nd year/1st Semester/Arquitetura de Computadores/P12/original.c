#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

typedef struct {
  int n;
  float *ptr;
} STRUCT;

#define SIZE 30000

STRUCT a[SIZE];

int initialize () {
  int i, j, total=0;
  double r;

  srand48 ((long int)1);

  for (i=0 ; i < SIZE ; i++) {
    r = drand48 ();
    a[i].n = (int)(r * (double)(i+10));
    total += a[i].n;
  }
  fprintf (stderr, "All n's initialized. There will be a total of %d elements\n", total);

  for (i=0 ; i < SIZE ; i++) {
    a[i].ptr = (float *) malloc (a[i].n * sizeof (float));
    if (a[i].ptr == NULL)
      return 0;
  }
  fprintf (stderr, "Successfully allocated memory for all a[].ptr\n");

  for (i=0 ; i < SIZE ; i++) {
    for (j=0 ; j<a[i].n ; j++) {
      a[i].ptr[j] = (float) ( drand48() * 10000.L ) + 1.0;
    } 
  }
  fprintf (stderr, "Successfully initialized all a[].ptr[]\n");
  return 1;
}

void release () {
  int i;

  for (i=0 ; i < SIZE ; i++) {
    free (a[i].ptr); 
    a[i].n = 0;
  }
}

/* O ciclo a vetorizar é o de dentro */
/* RAW - ptr[j] - ptr[j-1] */ 
/* WAR - a[i].ptr[j] */
void process () {
  int i, j;

  for (i=0 ; i< SIZE ; i++) {

    for (j=1 ; j <a[i].n ; j++) {
      float aux = a[i].ptr[j];

      a[i].ptr[j] = aux * aux *aux + 10.0 /aux + 120.0 / (aux *aux) + a[i].ptr[j-1] ;
    }
  }
}


void process_VEC() {
    int i, j;
    float temp[SIZE+10];
    for (i=0 ; i< SIZE ; i++) {
      float aux = a[i].ptr[j];
      temp[i] = aux * aux *aux + 10.0 /aux + 120.0 / (aux *aux);
    }
    for(j=1; j < a[i].n; j++) {
      a[i].ptr[j] = temp[j] + a[i].ptr[j-1];
    }
}

/* Versão paralelizada */
void process_OMP() {
  int i, j;
  #pragma omp parallel for private(j) schedule(dynamic)
  /* a estrutura "a" tem de ser partilhada entre threads */
  /* o aux é privado a cada thread */
  /* faz sentido que o "j" seja uma variável privada */
  for (i=0 ; i< SIZE ; i++) {
    for (j=1 ; j <a[i].n ; j++) {
      float aux = a[i].ptr[j];
      a[i].ptr[j] = aux * aux *aux + 10.0 /aux + 120.0 / (aux *aux) + a[i].ptr[j-1] ;
    }
  }
}

void process_VEC_OMP() {
    int i, j;
    float temp[SIZE+10];
    #pragma omp parallel for private(j, temp) schedule(dynamic)
    for (i=0 ; i< SIZE ; i++) {
      float aux = a[i].ptr[j];
      temp[i]= aux * aux *aux + 10.0 /aux + 120.0 / (aux *aux);
    }
    for(j=1; j < a[i].n; j++) {
      a[i].ptr[j] = temp[j] + a[i].ptr[j-1];
    }
  }
}




int main () {
  double T1, T2;

  // init data structure
  fprintf (stderr, "InicializaÃ§Ã£o... \n");
  if (!initialize ()) {
    fprintf (stderr, "...Erro ao inicializar estrutura!\n\n");
    exit (1);
  }
  fprintf (stderr, "... Sucesso!\n\n");

  // process data structure
  fprintf (stdout, "Processamento ...");
  T1 = omp_get_wtime();
  process();
  T2 = omp_get_wtime();
  fprintf (stdout, "sucesso! (%.1lf msecs)\n\n", (T2-T1)*1e3);

  // release data structure
  fprintf (stderr, "Libertar dados...");
  release ();
  fprintf (stderr, "... Sucesso!\n\n");
}
