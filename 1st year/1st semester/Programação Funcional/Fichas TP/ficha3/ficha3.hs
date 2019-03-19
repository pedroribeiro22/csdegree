module Ficha3 where

-- Exercicio 1
-- a
povoateList :: Int -> Int -> [Int]
povoateList x y | y > 0 = x : povoateList x (y - 1)
                | y == 0 = []

times :: Int -> Int -> Int
times x y = foldr (+) 0 a
        where a = povoateList x y

-- Exercicio 2
type Polinomio = [Monomio]
type Monomio = (Float, Int)

-- a
conta :: Int -> Polinomio -> Int
conta n p = length $ filter (\x -> x == n)  $ map snd p

-- b
grau :: Polinomio -> Int
grau p = maximum $ map snd p

-- c
selgrau :: Int -> Polinomio -> Polinomio
selgrau n p = filter (\x -> snd x == n) p

-- d
deriv :: Polinomio -> Polinomio
deriv p = map (\x -> (fst x * fromIntegral (snd x), snd x - 1)) p

-- e
calcula :: Float -> Polinomio -> Float
calcula v p = sum $ map (\x -> (v * fst x) ^ snd x) p

