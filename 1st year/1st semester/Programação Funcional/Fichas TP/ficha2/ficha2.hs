module Ficha2 where
import Data.Char

-- Exercício 2 --
-- a
dobros :: [Float] -> [Float]
dobros l = map (* 2) l

-- b
numOcorre :: Char -> String -> Int
numOcorre b l = length $ filter (== b) l

-- c
positivos :: [Int] -> Bool
positivos l = if (length a) == (length l) then True
              else False
            where a = filter (>= 0) l

-- d
soPos :: [Int] -> [Int]
soPos l = filter (>= 0) l

-- e
somaNeg :: [Int] -> Int
somaNeg l = sum $ filter (< 0) l

-- f
tresUlt :: [a] -> [a]
tresUlt l = if length l >= 3 then [last (init (init l)),last (init l),last l]
            else l

-- Exercicio 3
-- a
soDigitos :: [Char] -> [Char]
soDigitos l = filter (isDigit) l

-- b
minusculas :: [Char] -> Int
minusculas l = length (filter isLower l)

-- c
nums :: String -> [Int]
nums l = map (\x -> ord x - 48) $ filter isDigit l

-- Exercicio 4
-- a
segundos :: [(a, b)] -> [b]
segundos l = map snd l

-- b
nosPrimeiros :: (Eq a) => a -> [(a, b)] -> Bool
nosPrimeiros a l = elem a (map fst l)

-- c
