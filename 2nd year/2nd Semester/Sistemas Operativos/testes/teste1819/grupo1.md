# Grupo 1

## Exercício 1

    É um facto que *Round Robin* é a mais popular das rotinas de escalonamento
    principalmente devido à sua simplicidade de implementação e custos muito
    reduzidos de gestão.  Estabelecendo a comparação entre os dois: *Round
    Robin* e *Shortest Remaining Time*, o segundo implica claramente uma
    *queue* com prioridades, que por sua vez implica custos de manutenção.
    Clarificando: na segunda rotina de escalonamento, cada vez que o tempo de
    CPU é retirado a um processo, a fila de prioridades tem de ser atualizada,
    uma vez que o tempo remanescente para o processo em questão diminuiu.

## Exercício 2

    É um facto que podemos ter abertos programas cuja soma da memória
    necessária ultrapassa largamente a quantidade de memória RAM disponível.
    Isto é possível porque o contexto dos programas em segundo plano (que não
    têm CPU) é transferido para disco e fica em memória volátil apenas o
    endereço do disco onde está o contexto. Por outro lado, se mantivermos em
    primeiro plano (o contexto do programa fica em RAM) podemos, com relativa
    facilidade ultrapassar a quantidade de RAM disponível.
