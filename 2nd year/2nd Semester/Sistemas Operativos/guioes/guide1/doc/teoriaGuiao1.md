# Noções teóricas necessárias para realizar o Guião 1

## Ficheiros
    * O descritor do ficheiro é a representação abstrata de um ficheiro e é utilizado para operar sobre o mesmo.
    * Este mesmo descritor é representado por um inteiro não negativo.
    * Relativamente aos descritores é de reter que o descritor 0 corresponde ao Standard Input e o descritor corresponde ao Standard Output.

## Tabelas de processos e de ficheiros
    * Estrutura do Kernel
    * Guarda descritores de ficheiros abertos (em tabelas de processos)
    * A tabela de ficheiros é partilhada pelo sistema operativo
    * Guarda o modo de abertura e posição de leitura/escrita de cada descritor

## Bibliotecas
    * ```C <unistd.h>``` (definições e declarações de chamadas)
    * ```C <fcntl.h> ``` (definição modos de abertura de ficheiro)
    * O_RDONLY; O_WRONLY; O_CREAT; O_*;
