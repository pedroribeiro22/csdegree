module BinaryTrees where

import Data.Ord

-- Exercício 1 --
data BTree a = Empty
             | Node a (BTree a) (BTree a)
             deriving Show


altura :: BTree a -> Int
altura (Empty) = 0
altura (Node a Empty dir) = 1 + altura dir
altura (Node a esq Empty) = 1 + altura esq
altura (Node a esq dir) = max (altura esq) (altura dir)

contaNodos :: BTree a -> Int
contaNodos Empty = 0
contaNodos (Node a Empty dir) = 1 + contaNodos dir
contaNodos (Node a esq Empty) = 1 + contaNodos esq
contaNodos (Node a esq dir) = 1 + contaNodos esq + contaNodos dir

folhas :: BTree a -> Int
folhas Empty = 0
folhas (Node a Empty Empty) = 1
folhas (Node a esq dir) = folhas esq + folhas dir

prune :: Int -> BTree a -> BTree a
prune _ Empty = Empty
prune 1 (Node a esq dir) = Node a Empty Empty
prune d (Node a esq dir) = Node a (prune (d-1) esq) (prune (d-1) dir)

path :: [Bool] -> BTree a -> [a]
path _ Empty = []
path [] (Node a esq dir) = [a]
path (True:xs) (Node a esq dir) = a : path xs dir
path (False:xs) (Node a esq dir) = a : path xs esq

mirror :: BTree a -> BTree a
mirror Empty = Empty
mirror (Node a esq dir) = Node a (mirror dir) (mirror esq)

zipWithBT :: (a -> b -> c) -> BTree a -> BTree b -> BTree c
zipWithBT f Empty _ = Empty
zipWithBT f _ Empty = Empty
zipWithBT f (Node a esq dir) (Node a2 esq2 dir2) = Node (f a a2) (zipWithBT f esq esq2) (zipWithBT f dir dir2)

unzipBT :: BTree (a, b, c) -> (BTree a, BTree b, BTree c)
unzipBT Empty = (Empty, Empty, Empty)
unzipBT (Node (a, b, c) esq dir) = (Node a ae ad, Node b be bd, Node c ce cd)
            where
                ae = fstT $ unzipBT esq
                ad = fstT $ unzipBT dir
                be = sndT $ unzipBT esq
                bd = sndT $ unzipBT dir
                ce = trdT $ unzipBT esq
                cd = trdT $ unzipBT dir

fstT :: (a, b, c) ->  a
fstT (x, y, z) = x

sndT :: (a, b, c) -> b
sndT (x, y, z) = y

trdT :: (a, b, c) -> c
trdT (x, y, z) = z

-- Exercício 2 --
minimo :: Ord a => BTree a -> a
minimo (Node a Empty _) = a
minimo (Node a esq dir) = minimo esq

semMinimo :: Ord a => BTree a -> BTree a
semMinimo (Node a Empty Empty) = Empty
semMinimo (Node a Empty dir) = dir
semMinimo (Node a esq dir) = Node a (semMinimo esq) dir

minSmin :: Ord a => BTree a -> (a, BTree a)
minSmin (Node a Empty Empty) = (a, Empty)
minSmin (Node a Empty dir) = (a, dir)
minSmin (Node a esq dir) = (fst $ minSmin esq, snd $ minSmin dir)




























