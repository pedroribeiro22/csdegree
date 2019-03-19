module Ficha2 where
import Data.Char
import Data.List

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
minFst :: (Ord a) => [(a, b)] -> a
minFst l = minimum $ map fst l

-- d
sndMinFst :: (Ord a) => [(a, b)] -> b
sndMinFst l@(x:xs) = if a == fst x then snd x
                   else sndMinFst xs
            where a = minFst l

-- e
get1st :: (Num a, Num b, Num c) => (a, b, c) -> a
get1st (x, y, z) = x

get2nd :: (Num a, Num b, Num c) => (a, b, c) -> b
get2nd (x, y, z) = y

get3rd :: (Num a, Num b, Num c) => (a, b, c) -> c
get3rd (x, y, z) = z

sumTriplos :: (Num a, Num b, Num c) => [(a, b, c)] -> (a, b, c)
sumTriplos l = (sum $ map get1st l, sum $ map get2nd l, sum $ map get3rd l)

-- f
sumTriplo :: (Ord a, Num a) => (a, a, a) -> a
sumTriplo (x, y, z) = x + y + z

maxTriplo :: (Ord a, Num a) => [(a, a, a)] -> a
maxTriplo l = maximum $ map sumTriplo l
