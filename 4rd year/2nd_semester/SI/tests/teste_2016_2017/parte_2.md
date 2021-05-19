# Pergunta 1

De acordo com o enunciado a usabilidade é definida como a facilidade com que um
produto pode ser utilizado para atingir determinados objetivos de forma efetiva,
eficiente e satisfatória num determinado contexto de uso. Tendo em conta os dois
casos de uso referido é relativamente óbvio que as interfaces propostas se
adaptam melhor a um do que outro de acordo com o contexto. Se o objetivo for
saber qual o programa da conferência para um determinado dia é óbvio que a
interface com tabbed panes apresenta vantagem, visto que permite simplesmente
selecionar o dia pretendido e filtrar assim os eventos que tomaram lugar. Sendo
que essa é a tarefa, com esta interface é mais fácil realizá-la de forma efetiva
e mais rápida, o que se traduz numa boa sensação para o utilizador. Por outro
lado, se o objetivo for procurar artigos de um determinado autor a primeira
interface é melhor porque centralizamos numa só página todos os artigos e não
corremos o risco de um artigo do autor estar num dia da conferência que não
corresponde ao pane em que nos encontramos. Esta interface, corresponde para o
caso mencionado, a um uso melhor dos recursos (neste caso tempo) uma vez que,
dependendo do grau de conhecimento do utilizador poderia simplesmente fazer
CTRL+F e procurar pelo nome do autor. No entanto, quer o grau de instrução do
utilizador seja alto ou baixo torna-se claro que neste caso de uso esta
interface é mais eficiente e menos suscetível a que escape algum artigo do autor
pretendido.

# Pergunta 2

A maneira mais imediata é a utilização de media queries em CSS. Conforme o ecrã
em que estivermos podemos ajustar a propriedade `display` para cada uma das
implementações. Ou seja basicamente, teríamos ambas as implementações
codificadas, cada uma associada a uma classe CSS e dependendo do tamanho do ecrã
em que o conteúdo é apresentado fazemos: `display: none` numa das implementações.

# Pergunta 3

Se analisarmos a questão pelo prisma da rapidez de resposta a tecnologia
server-side poderá até servir páginas de um modo mais imediato já que para obter
a página completa basta efetuar um pedido ao servidor e aguardar pela resposta,
que, neste caso, será já a página completa. No caso da arquitetura client-side
teríamos o pedido ao servidor cuja resposta seria a página estática e depois
teríamos ainda de pedir cada um dos elementos de dados através da execução do
código do lado do cliente. A experiência na arquitetura client-side poderá, no
entanto, ser melhor, já que esta proporciona uma interação mais reativa (em
consequência de o código correr no cliente). Por último, em termos de
custo/facilidade de implementação/manutenção, a arquitetura client-side leva,
nos dias que correm, larga vantagem uma vez que, apesar de existirem várias
frameworks para se aprender, existem imensos recursos, já para não falar que a
implementação de JSP quebra os principios mais fundamentais da programação
orientada aos objetos. Existem librarias especializadas em reutilização de
código (componentes), pelo que o código se torna mais rápido de escrever e mais
fácil de manter. Aliado a todos estes fatores temos o facto de existirem CDN
(Content Delivery Networks) que permitem servir conteúdo estático com latências
e períodos de resposta muito baixos, o que contribui para melhorar imenso a
experiência de utilização na arquitetura client-side. Por todos estes pontos, a
arquitetura client-side leva, nos tempos que correm, clara vantagem.
