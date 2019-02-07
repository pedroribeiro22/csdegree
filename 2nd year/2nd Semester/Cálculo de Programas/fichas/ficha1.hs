module Ficha1 where

-- 2 --
--a--
myLength :: [a] -> Int
myLength [] = 0
myLength (h:t) = 1 + myLength t

--b--
myReverse :: [a] -> [a]
myReverse [h] = [h]
myReverse l = [last l] ++ myReverse (init l)

-- 3 --
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Nothing : xs) = catMaybes xs
catMaybes (Just b : xs) = b : catMaybes xs

-- 4 --
-- a --
myUncurry :: (a -> b -> c) -> (a, b) -> c
myUncurry f (x,y) = f x y

-- b --
myCurry :: ((a, b) -> c) -> a -> b -> c
myCurry f x y = f (x, y)

-- c --
myFlip :: (a -> b -> c) -> b -> a -> c
myFlip f x y = f y x

-- 5 --
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

-- 6 --
newLength :: [a] -> Int
newLength = foldr (\ _ n -> 1 + n) 0

cenas :: [a] -> [a]
cenas l = foldr (:) [] l

-- 7 --
myConcat :: [[a]] -> [a]
myConcat [] = []
myConcat (h:t) = h ++ myConcat t

-- 8 --
function :: [Int] -> [Int]
function l = foldr f [] l
      where
        f x ac | x > 0 = (x+1) : ac
               | True = ac

-- 9 --
-- a --
funcaoExemplo :: (a -> b) -> [a] -> [b]
funcaoExemplo f [] = []
funcaoExemplo f (h:t) = f h : funcaoExemplo f t

-- Não estou a ver --

-- b --
funcaoExemploNew :: (a -> b) -> [a] -> [b]
funcaoExemploNew f l = map f l
