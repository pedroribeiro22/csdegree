#include <stdio.h>
#include <stdlib.h>

/**
\brief  Definição da estrutura de dados sugerida
    @var start Início do intervalo de lugares reservados.
    @var finish Fim do intervalo de lugares reservados.
*/
typedef struct intv {
    int start;
    int finish;
    struct intv *prox;
} *IntV;

