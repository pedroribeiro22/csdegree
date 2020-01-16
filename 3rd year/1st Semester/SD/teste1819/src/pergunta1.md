# Pergunta 1

## Distinga _escala geográfica_ de _escala numérica_ em sistemas distribuídos e identifique técnicas usadas para as atingir.

Escala geográfica refere-se à área abrangida pelo sistema, a distância entre os
diversos componentes do sistema.

Escala numérica refere-se ao número de componentes do sistema, tanto servidores
como clientes.

Algumas técnicas usadas para atingir estas escalas são:

 * DHT: Técnica de naming que não tem as restrições numéricas do flat naming
e não tem os problemas de bottleneck do hierarquical naming.

 * Multicast através de gossip: Enviar uma mensagem a todos não resultaria
com um número arbitrariamente grande de elementos. De modo a resolver este
problema enviamos a mensagem a um número limitiado de elementos do sistema
e utilizando gossip (comunicação da mensagem por parte dos sistemas que a
receberam a sistemas vizinhos) a mensagem espalha-se, eventualmente, a
todos os elementos do sistema distribuído.

 * Utilização de arquitetura decentralized: Evita o bottleneck (nesta caso
significando sobrecarga) do server e evitar o single point of failure
(pelo facto de se deixar de depender unicamente do server. Com esta
técnica o comprometimento de uma parte do sistema não implica o
comprometimento total do sistema.)

