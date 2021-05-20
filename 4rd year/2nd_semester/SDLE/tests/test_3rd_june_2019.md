# Sistemas Distribuídos em Larga Escala

## 1. Indique quais os grafos que conhece que não exibam ciclos e identifique o que lhe parecer mais apropriado para o _broadcast_ de mensagens.

Os tipos de grafos que conhecemos que não apresentam ciclos são os grafos do
tipo: _ring_, _star_ e _tree_.

A tipologia de grafo que mais se adapta ao _broadcast_ de mensagens é a
tipologia de árvore uma vez que a sua estrutura é apropriada à propagação de
mensagens entre nodos e é relativamente fácil encontrar uma terminação no
processo. Um nodo _root_ poderia comunicar uma mensagem aos seus filhos e os
seus filhos comunicá-la-iam aos seus filhos e quando todos os filhos desse nodo
enviarem de volta um _ack_ o processo filho pode comunicar ao _root_ que o
_broadcast_ foi concluído de modo bem sucessido. Existem, inclusivamente, outras
maneiras de realizar o _broadcast_ de mensagens neste tipo de tipologia com a
implementação de SyncBFS. Uma mensagem com um payload _m_ pode ser associada à
construção da SyncBFS e cada filho comprometem-se a devolver um _ack_ ao seu
_parent_ assim que todos os seus filhos lhe enviarem um _ack_.

## 2. Apresente uma estratégia para a determinação do diâmetro num grafo que sabemos possuir uma única _connected component_.

Uma estratégia para a determinação do diâmetro passa pela construção de um
SyncBFS. Sendo que temos uma _connected component_, temos também a garantia de
que o diâmetro dessa componente será igual ao diâmetro do grafo. Cada nodo
constrói a sua SyncBFS e calcula o seu _longest path_ (que é igual ao seu
diâmetro). Depois basta calcular o máximo de todos esses valores e temos o
diâmetro do grafo.

## 3. Explique de que modo os bloom filters permitiram melhorar a escalabilidade da rede Gnutella.

A versão original do Gnutella apresentava problemas de escalabilidade
principalmente pela técnica que era utilizada para descobrir novos nodos. Para
descobrir novos nodos eram enviados PINGs na rede a que os novos nodos
respondiam com PONGs. Este algoritmo de descoberta de novos nodos é,
essencialmente, _flooding_ que coloca uma enorme (e desnecessária) carga na
rede. Sendo assim, a evolução foi a criação de uma rede com duas camadas: os
_superpeers_ e os _peers_ normais. Os _superpeers_ passam a guardar um _bloom
filter_ para cada _key_. Os _peers_ passam a conectar-se aos _peers_ para
descobrir um conjunto de nodos com elevada probabilidade de terem o conteúdo que
este pretende obter. A escalabilidade aumenta imenso na medida em que
desapareceu o _flooding_ e deu lugar a uma pesquisa localizada e apenas são
estabelecidos contactos com nodos com elevada probabilidade de serem contentores
do conteúdo desejado.

## 4. Indique qual a relevância da latência da rede na sincronização de relógios (_wall clocks_) num sistema distribuído.

A latência da rede é essencialmente a materialização do tempo de trânsito das mensagens na rede. No entanto, o valor da latência
pode ser utilizado para sincronizar os relógios, embora a implementação deste algoritmo dependa do modelo de sistema que estamos a considerar: síncrono ou assíncrono.

No modelo síncrono, sabendo o tempo de trânsito da mensagem (latência) e o tempo
de origem da mensagem (t) poderíamos considerar que o novo valor do relógio
seria: t + trans. No entanto, sabemos que o tempo de trânsito varia dentro de
certos limities (neste modelo de sistema). Assim, percebemos percebemos que a
incerteza é u = tmax - tmin. No entanto, se considerarmos t + (tmin + tmax) / 2
a incerteza passa para metade.

No entanto, quando consideramos o modelo de sistema assíncrona não assumimos
nenhum limite superior para o tempo de trânsito da mensagem. Consideramos uma
mensagem enviada no instante t e medimos o tempo que a demora a chegar e
denominamos esse intervalo de round trip time (rtt). Assim (e assumindo que o
tempo que a mensagem demora a alcançar o destino é o mesmo que demora a voltar)
podemos considerar que t + rtt / 2. Por forma a alcançar a maior precisão
desejável o processo pode ser repetido até que rtt seja tão baixo quanto
possível.

## 5. Explique que tipo de problemas podem advir da utilização de relógios (wall clocks) na implementação multi-master de um relógio distribuído.

A utilização de wall clocks na utilização de relógios na implementação
multi-master de um relógio distribuído tem problemas óbivos que podem surgir no
caso de os relógios não estarem devidamente sincronizados.

Consideremos um registo distribuído em que utilizamos para a implementação do
CRDT uma política de Last Writer Wins. A avaliação de quem é realmente o Last
Writer depende diretamente das etiquetas temporais que são atribuídas por cada
um dos processos que tentou alterar o registo. Consideremos que um processo que
tem o relógio adiantado 1 hora relativamente ao outro processo decide escrever
um valor no registo. Passados 30 minutos o outro processo tenta escrever no
mesmo registo, no entanto, nessa altura o seu relógio marcará a mensagem com uma
tag que é 30 minutos anterior à que o valor que está no registo tem, pelo que a
escrita não sortirá efeito. Ou seja, uma escrita que realmente sucede outra não
está a surtir efeito, pelo que a política de Last Writer Wins não está a ser bem
aplicada em virtude da dessincronia dos relógios.
