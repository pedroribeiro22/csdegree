## Pergunta 1

A comunição síncrona implica o receção sequencial da resposta a um pedido. Esta
pode ser preconizada por operações como `x = metodo()`, que implica, neste
caso, que após a realização do pedido este bloqueie à espera da resposta. Este
tipo de comunicação não serviria nunca para implementar sistemas distribuídos
uma vez que implicaria enormes _delays_ nas respostas a pedidos e levaria a
tempos de espera absolutamente incomportáveis.  A comunicação assíncrona, por
outro lado, corresponde a processos em que um processo lança um pedido e esse
pedido pode não ser imediatamente tratado, pode ser remetido para uma _pool_ de
pedidos que pode ter implementadas as mais diversas políticas no que diz
respeito ao atendimento de pedidos (FIFO, LIFO, prioridades...) (significa que
as mensagens devem ser armazenadas pelo _middleware_. Este último é útil para a
implementação de sistemas distribuídos na medida em que permite implementar
justiça no atendimento pedidos de diversos processos e desta forma regular, por
exemplo, os tempos de espera.
Para o primeiro caso temos como exemplo de _middleware_ o uso de um cliente
single-threaded e um servidor que single-threded.  Para o segundo caso temos
como exemplo de _middleware_ o uso de um cliente multithreaded com um
dispatcher e um servidor multithread com uma pool de pedidos e que dedica uma
thread a cada pedido recolhido da pool (reutilizando threads para mitigar a
inefici|encia que se criaria caso tivesse que criar uma thread para cada
pedido.)


## Pergunta 2

A transparência de acesso, em termos simples, corresponde ao encapsulamento das
camadas de acesso ao servidor por parte de uma camada de software que atua como
se o cliente estivesse a aceder ao próprio objeto. Esta transparência é
implementada por _stubs_ que são finas camadas de software que escondem a
ligação com a parte remota e encaminham o pedido para as camadas de software
devidas. A invocação remota pode ser vista assim como a invocação de um método
da camada de negócio que está, no entanto, _wrapped_ pelo _stub_.


## Pergunta 3

A importância do _relógio lógico de Lamport_ é imensa em sistemas distribuídos.
Este relógico propõe uma solução, que é bastante eficaz para o problema da
sincronização dos relógios (resolvendo o problema do _skew_ constante dos
relógios baseados no tempo, como o conhecemos). Permite, por exemplo, o uso
correto das regiões de exclusão mútua, permitindo deste modo ter processos
paralelos.  Ambos os interveninetes numa transação têm uma versão do relógio
lógico: aquando da receção da mensagem é calculado o máximo entre o valor no
relógio lógico do recetor e o valor no relógio lógico do que envia e somado 1
(correspondente à mensagem que acabou de ocorrer), desta forma é garantido que
cada mensagem é eticada com uma marca temporal única, o que permite uma visão
temporal dos eventos correta e clara.
