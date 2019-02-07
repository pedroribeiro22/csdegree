module Ficha1 where

-- Exercício 1 --
-- a --
{-
1º caso
(f . g) x = f (g x) = f (x + 1) = 2 * (x + 1) = 2 * x + 2 * 1 = 2x + 2
2º caso
(f . g) x = f (g x) = f (2 * x) = succ (2x) = 2x + 1
3º caso
(f . g) x = f (g x) = succ (length) = length + 1
4º caso
(f . g) x y = f (g(x, y)) = f (x + y) = succ . (*2) (x + y) = (*2) (x + y + 1) = 2x + 2y + 2
-}

-- Exercício 2 --
--a--
myLength :: [a] -> Int
myLength [] = 0
myLength (h:t) = 1 + myLength t

--b--
myReverse :: [a] -> [a]
myReverse [h] = [h]
myReverse l = [last l] ++ myReverse (init l)

-- Exercício 3 --
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Nothing : xs) = catMaybes xs
catMaybes (Just b : xs) = b : catMaybes xs

-- Exercício 4 --
-- a --
myUncurry :: (a -> b -> c) -> (a, b) -> c
myUncurry f (x,y) = f x y

-- b --
myCurry :: ((a, b) -> c) -> a -> b -> c
myCurry f x y = f (x, y)

-- c --
myFlip :: (a -> b -> c) -> b -> a -> c
myFlip f x y = f y x

-- Exercício 5 --
data LTree a = Leaf a
             | Fork (LTree a, LTree a)


-- a --
flatten :: LTree a -> [a]
flatten (Leaf b) = [b]
flatten (Fork (e, d)) = flatten e ++ flatten d

-- b --
mirror :: LTree a -> LTree a
mirror (Leaf b) = Leaf b
mirror (Fork (e, d)) = Fork (mirror d, mirror e)

-- c --
myFmap :: (b -> a) -> LTree b -> LTree a
myFmap f (Leaf i) = Leaf (f i)
myFmap f (Fork (e, d)) = Fork (myFmap f e, myFmap f d)

-- Exercício 6 --
newLength :: [a] -> Int
newLength = foldr (\ _ n -> 1 + n) 0

cenas :: [a] -> [a]
cenas l = foldr (:) [] l

-- Exercício 7 --
myConcat :: [[a]] -> [a]
myConcat [] = []
myConcat (h:t) = h ++ myConcat t

-- Exercício 8 --
function :: [Int] -> [Int]
function l = foldr f [] l
      where
        f x ac | x > 0 = (x+1) : ac
               | True = ac

-- Exercício 9 --
-- a --
funcaoExemplo :: (a -> b) -> [a] -> [b]
funcaoExemplo f [] = []
funcaoExemplo f (h:t) = f h : funcaoExemplo f t

fr :: (a -> b) -> [a] -> [b]
fr f = foldr (\x acc -> f x : acc) []

-- b --
funcaoExemploNew :: (a -> b) -> [a] -> [b]
funcaoExemploNew f l = map f l
