<h1 align="center">
  <a target="_blank" href="">
    <img src="https://cdn-images-1.medium.com/max/1600/1*KtsVK65nfJz8MohWwdEHWQ.png" alt="christmas" height="200px" width="280px">
  </a>
</h1>

# 1. Introdução aos *folds*
## 1.1 Motivação

Consideremos as seguintes definições de 2 funções extremamente simples: sum e reverse, que atuam sobre listas.

```haskell
sum :: [Int] -> Int 
sum [] = 0
sum (h:t) = h + sum t
```

```haskell
reverse :: [Int] -> [Int]
reverse [] = []
reverse (h:t) = reverse t ++ [h]
```

É relativamente fácil notar aqui um padrão: quando a lista não é vazia, começamos por invocar recursivamente a função sobre a cauda da lista, sendo o resultado desta operação posteriormente combinado com a operação realizada sobre o elemento da lista a considerar.

## 1.2 O primeiro *fold*
Já todos conhecemos o que é um fold visto que abordamos o vertente tópico no ano passado na disciplina de _**Programação Imperativa**_.

Consideremos o exemplo do *foldr* que é uma função do género:

```haskell
foldr :: (a->b->b) -> b -> [a] -> b
foldr f z [] = z
foldr f z (h:t) = f h (foldr f z t)
```

Escrevemos então a função _**sum**_ usando um foldr:
```haskell
sum :: [Int] -> Int 
sum = foldr (+) 0
```

Tentemos fazer algo semelhante com a função _**length**_ que calcula o comprimento de uma lista.
Consideremos esta definição muito simples da função referida acima:
```haskell
length :: [Int] -> Int 
length [] = 0
length (h:t) = 1 + length t 
```

Tentemos implementar a mesma função usando um foldr:
```haskell
length :: [Int] -> Int 
length l = foldr f 0 l
    where f x y = y + 1
```

## 1.3 *Folds* sobre árvores

Consideremos agora o seguinte *Data type* para representar árvores binárias:
```haskell
data Tree a = Empty
            | Node a (Tree a) (Tree a)
```

Temos a funçaão que calcula a altura da árvore:
```haskell
height :: Tree a -> Int 
height Empty = 0
height (Node _ l r) = 1 + max (height l) (height r)
```

Temos a função que efetua uma travessia *in-order* pela árvore:
```haskell
inorder :: Tree a -> [a]
inorder Empty = []
inorder (Node x l r) = (inorder l) ++ [x] ++ (inorder r)
```

Tal como no primeiro exemplo conseguimos notar um padrão, que nos leva a deduzir um *fold* do tipo:
```haskell

```