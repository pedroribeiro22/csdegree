# Teste Modelo 2018 - 2019

## Exercício 1

```C
#pragma omp parallel
{ int i, first=false, tid = omp_get_thread_num ();
 double T;
 printf (“Thread %d starting\n”, tid);
 #pragma omp for
for (i=0; i < 300000 ; i++) do_work(i);
 #pragma omp single
 { T = omp_get_time ();
first = true;
printf (“Thread %d work done\n”, tid);
 }
 if (first) printf (“1st finished in %.0lf us\n”, (omp_get_wtime()-T)*1e6);
 printf (“Thread %d finishing\n”, tid);
}
```

Sabemos que a primeira thread a atingir a zona delimitado pela diretiva  ```#pragma omp single```  terá de imprimir ``` Thread (whatever number it is) work done```. 

Com um pouco mais de análise concluímos que única alternativa válida é:

```
Thread 1 starting
Thread 0 starting
Thread 2 starting
Thread 0 work done
Thread 2 finishing
1st finished in 7 us
Thread 1 finishing
Thread 0 finishing
```

## Exercício 2

O ganho de desempenho obtido com a vectorização do código, relativamente à respectiva versão escalar, deve-se:

    * à diminuição do número total de instruções executadas (#I)

## Exercício 3

As unidades de processamento gráfico minimizam o impacto dos acessos à memória no desempenho dos programas:

    * comutando rapidamente entre grupos de threads, sobrepondo o tempo de acesso de algumas threads com a execução de outras

## Exercício 4

O loop unrolling tem potencial para disponibilizar mais instruções para execução em paralelo num contexto de superescalaridade. Para o código abaixo selecione a opção de *unrolling* que disponibiliza potencialmente mais *instruction level parallelism.*

```C
    int a[SIZE], i, sum=0;
    for(i = 0; i < SIZE; i++) sum += a[i];
```

```C
    int a[SIZE], i, sum=0, sum_a=0;
    for(i = 0; i < SIZE; i++) 
    {
        sum_a += a[i];
        sum += a[i+1];
        sum += sum_a;
    }
```

Uma vez que é a única opção onde não se verificam dependências de dados do tipo RaW (Read after Write).

## Exercício 5

**UF1(Op)** - realiza operações lógicas e aritméticas sobre inteiros;

**UF2(LS + B)** - realiza acessos à memória (Load/Store) e saltos (branches);

Considere que cada uma destas unidades funcionais executa **uma instrução por ciclo de relógio (isto é, não há nenhuma operação que exija mais do que um ciclo do relógio na respective unidade funcional). Considere ainda o seguinte excerto de código:

```assembly
    I1: movl (%ebx, %ex, 4), %esi
    I2: addl %esi, %eax
    I3: incl %edx
    I4: decl %ecx
    I5: jnz I1
```

## Exercício 6

O código apresentado abaixo, que explora *Thread Level Parallelism* recorrendo ao OpenMP, pretende calcular a soma de alguns elementos de cada linha `i` de uma matriz (elementos das colunas 1 a i) e armazenar o resultado no primeiro elemento dessa linha (`a[i][0]`):

```C
#define W 400000
int a[W][W];
int sum, i, j;

#pragma omp parallel for
for(i = 0; i < W; i++) {
    sum = 0;
    for(j = 1; j < i; j++) sum += a[i][j];
    a[i][0] = sum;
}
```

Parece-me evidente que o comportamento indefinido deste programa se deve à existência de *racing conditions* causadas pelo acesso e alteração imprevisível a dados partilhados por várias threads.

Este problema tem, na verdade, uma solução bastante simples considerando as diretivas do OpenMP lecionadas no decurso da cadeira. Notemos que precisamos de um `j` para cada `i` assim como uma variável `sum`. Podemos obter as condições previamente mencionadas fazendo as seguintes alterações no código:

```C
#define W 400000
int a[W][W];
int sum, i, j;

#pragma omp parallel for private(j, sum)
for(i = 0; i < W; i++) {
    sum = 0;
    for(j = 1; j < i; j++) sum += a[i][j];
    a[i][0] = sum;
}