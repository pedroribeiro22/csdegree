# Pergunta 2

## Defina _transparência de acesso_ e explique em que medida é que a _invocação remota_ (RPC) contribui para obter.

A transferência de acesso é implementada por _middleware_ que esconde a
interação entre cliente e servidor como um procedimento/método. O _middleware_
esconde a comunicação com sockets, serialização de objetos e serviço de nomes.
O código do _stub_ e do servidor é mecanicamente determinado pelo protocolo da
interface.  Os parâmetros passados são copiados do cliente para o servidor. Os
erros de conexão, no entanto, não podem ser escondidos.  O servidor regista o
nome no serviço de nomes (`NAMING`). O _stub_ procura o _address_ através do
nome (encapsulado no código do cliente).
