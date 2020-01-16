# Pergunta 3

## Explique uma forma de mitigar a incerteza quanto ao tempo de transmissão de mensagens para conseguir sincronizar relógios em sistemas distribuídos.

Para o funcionamento dos sistemas distribuídos, que são uma coleção de
elementos autónomos de computação. O funcionamento destes sistemas apenas é
possível desde que os relógios do hardware estejam coordenados.
Os relógios de hardware não são perfeitos e fazem muitos _drift_. O desacerto
dos relógios podem ser um problema com ficheiros partilhados e com determinados
algoritmos.
Pode usar-se NTP (Network Time Protocol) que assume que o delay no envio das
mensagens é sempre o mesmo ((T2 - T1) + (T4 - T3)) / 2 e repete o processo
várias vezes escolhendo o menor intervalo T4 - T1.
