# Teste Modelo 2017 - 2018

## Exercício 1

Discuta a veracidade da afirmação abaixo, justificando a sua resposta:
*"O ganho de desempenho obtido com a vetorização de código, relativamente à respectiva versão escalar, deve-se apenas à diminuição do número médio de ciclos por instrução (CPI)."*

```
A afirmação é evidentemente falsa. Com a introdução das instruções vetoriais, que atuam sobre um conjunto de dados de modo simultâneo, a complexidade das mesmas tende a aumentar. Não é difícil simpatizar com esta ideia na medida em que basta apenas pensar que é aplicada uma operação elementar a um conjunto de dados. Com estas ideias torna-se evidente que a vantagem da vetroização se encontra no facto de se diminuir de modo drástico o número de instruções, diminuição esta que compensa o aumento do CPI, de tal modo, que o tempo de execução tende a diminuir.
```

## Exercício 2

Para cada um dos ciclos abaixo indique justificando se é vectorizável. Se identificar dependências de dados entre iterações calcule a respectiva distância e indique o seu tipo (Write after Read (WaR) ou Read After Write (RaW)).

```C
struct {int g, h; } a[SIZE];

for(i = 0; i < SIZE-1; i++)
    a[i].g = 20 * a[i+1].h;
```
Evidentemente nem precisamos de analisar o tipo de dependências deste código para perceber que o mesmo não é vetorizável. O facto mencionado anteriormente é trivialmente verdadeiro uma vez que estamos perante uma estrutura de dados que é bloqueadora de auto-vetorização (Array of Structs), uma vez que dados não estão dispostos de forma contígua.

Analisemos agora as possíveis dependências de dados:

Como temos que `i` aumenta podemos calcular `d` do seguinte modo: `d = c^W - c^R`.

Assim: `d = i - (i+1) = -1`

Como `d < 0` temos uma dependência do tipo WaR (Write after Read) que por si só não seria um obstáculo à vetorização.

```C
float a[SIZE];

for(i = SIZE-1; i >= 2; i--)
    a[i] = 5 * a[i] / a[i-2];
```

Estamos perante uma estrutura de dados que tipicamente permite vetorização, um array, onde os dados estão dispostos de forma contígua. O `stride` é 1, evidentemente. Não existem estruturas condicionais, não existem invocações de funções não intrínsecas e o número de iterações pode, com facilidade, ser computado à partida.

Vejamos qual o tipo de dependências:

Como temos que `i` diminui podemos calcular `d` do seguinte modo: `d = - (c^W - c^R)`.

Assim: `d = - (i - (i+2)) = -2`

Como `d < 0` temos uma dependência do tipo WaR (Write after Read) que por si só não é um obstáculo à auto-vetorização.

Assim este trecho é vetorizável.

## Exercício 3

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
 if (first) printf (“1st finished %.0lf us before\n”, (omp_get_wtime()-T)*1e6);
 printf (“Thread %d finishing\n”, tid);
}
```
A opção A é claramente excluída porque claramente não podemos ter um print de "Thread 0 work done" quando temos "Thread 2 starting" a seguir disso. Como a diretiva ```C #pragma omp single``` implica um alinhamento das threads em termos temporais é impossível que primeiro print mencionado aconteça antes do segundo.

A opção C é excluída também pelo facto de termos o print de "Thread 2 finishing" antes de termos um print "Thread (wutever) work done" pelas mesmas razões referidas anteriormente.

Deste modo, por exclusão de partes, a opção B é a correta.

## Exercício 4

```C
#define S 1000000
float a[S];
int i, j;
for(i = 1; i < S; i++) {
    a[i] = 0.;
    for(j = 0; j < i; j++) {
        a[i] += j;
    }
}
```

Como estamos perante um ciclo for temos de usar a diretiva `for` inerente ao OpenMP para podermos executar este código de forma paralela. No entanto, para que possamos fazer tal coisa precisamos que a variável `j` seja privada para cada thread atribuída à execução paralela. Precisamos de ter um `j` para cada thread para podermos iterar o ciclo sem que as threads se afetem mutuamente levando a um resultado indeterminado. Assim a versão adaptada do código seria:

```C
#define S 1000000
float a[S];
int i, j;
#pragma omp parallel for private(j)
for(i = 1; i < S; i++) {
    a[i] = 0.;
    for(j = 0; j < i; j++) {
        a[i] += j;
    }
}
```
