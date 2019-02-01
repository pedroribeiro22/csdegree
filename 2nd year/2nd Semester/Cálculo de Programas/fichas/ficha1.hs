module Ficha1 where

-- Length function using a *foldl*
-- increment :: Int -> Int
-- increment x = x+1

-- myLength :: [a] -> Int
-- myLength (x:xs) = foldl (increment) 0 xs

-- catMaybes
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Nothing : xs) = catMaybes xs
catMaybes (Just a : xs) = a : catMaybes xs

-- uncurry
myUncurry :: (a->b->c) -> (a,b) -> c
myUncurry f (a,b) = f a b

-- curry
myCurry :: ((a,b) -> c) -> a -> b -> c
myCurry f a b = f (a, b)

-- flip
myFlip :: (a->b->c) -> (b->a->c)
myFlip f a b = f b a

-- LTrees
data LTree a = Leaf a
              | Fork (LTree a, LTree a)
              deriving Show

flatten :: LTree a -> [a]
flatten (Leaf a) = [a]
flatten (Fork (a, b)) = flatten a ++ flatten b

mirror :: LTree a -> LTree a
mirror (Leaf a) = Leaf a
mirror (Fork (a, b)) = Fork (mirror a, mirror b)

treeMap :: (b->a) -> LTree b -> LTree a
treeMap f (Leaf x) = Leaf (f x)
treeMap f (Fork (x, y)) = Fork (treeMap f x, treeMap f y)

-- concat
myConcat :: [[a]] -> [a]
myConcat [] = []
myConcat (x:xs) = x ++ myConcat xs


