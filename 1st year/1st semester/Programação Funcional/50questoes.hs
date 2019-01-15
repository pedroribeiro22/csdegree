-- 50 questões Programação Funcional --

import Prelude
import Data.List
import Data.Char

-- Questão 1 --
myEnumFromTo :: Int -> Int -> [Int]
myEnumFromTo x y | x > y = []
                 | x <= y = x : myEnumFromTo (x+1) y

-- Questão 2 --
myEnumFromThenTo :: Int -> Int -> Int -> [Int]
myEnumFromThenTo x y z | x > z = []
                       | x <= z = x : myEnumFromThenTo (x+a) (y+a) z
                       where a = y-x

-- Questão 3 --
myPlusPlus :: [a] -> [a] -> [a]
myPlusPlus [] l = l
myPlusPlus l1 l2 = myPlusPlus (init l1) (last l1 : l2)

-- Questão 4 --
myBangBang :: [a] -> Int -> a
myBangBang (x:xs) 0 = x
myBangBang l n = myBangBang (tail l) (n-1)

-- Questão 5 --
myReverse :: [Int] -> [Int]
myReverse [] = []
myReverse l = last l : myReverse (init l)

-- Questão 6 --
myTake :: Int -> [a] -> [a]
myTake 0 l = []
myTake n l = (head l) : myTake (n-1) (tail l)

-- Questão 7 --
myDrop :: Int -> [a] -> [a]
myDrop 0 l = l
myDrop n l = myDrop (n-1) (tail l)

-- Questão 8 --
myZip :: [a] -> [b] -> [(a, b)]
myZip [] l2 = []
myZip l1 [] = []
myZip (x:xs) (y:ys) = (x, y) : myZip xs ys

-- Questão 9 --
myElem :: Eq a => a -> [a] -> Bool
myElem x [] = False
myElem x (y:ys) = if x == y then True
                  else myElem x ys

-- Questão 10 --
myReplicate :: Int -> a -> [a]
myReplicate 0 x = []
myReplicate n x = x : myReplicate (n-1) x

-- Questão 11 --
myIntersperse :: a -> [a] -> [a]
myIntersperse x [y] = [y]
myIntersperse x (y:ys) = y : x : myIntersperse x ys

-- Questão 13 --
myConcat :: [[a]] -> [a]
myConcat [] = []
myConcat (x:xs) = x ++ myConcat xs

-- Questão 14 --
myInits :: [a] -> [[a]]
myInits [] = [[]]
myInits l = myInits (init l) ++ [l]

-- Questão 15 --
myTails :: [a] -> [[a]]
myTails [] = [[]]
myTails l  = [l] ++ myTails (tail l)

-- Questão 16 --
myIsPrefixOf :: Eq a => [a] -> [a] -> Bool
myIsPrefixOf l [] = False
myIsPrefixOf [] l = True
myIsPrefixOf (x:xs) (y:ys) = if x == y then myIsPrefixOf xs ys
                             else False

-- Questão 17 --
myIsSuffixOf :: Eq a => [a] -> [a] -> Bool
myIsSuffixOf l [] = False
myIsSuffixOf [] l = True
myIsSuffixOf l1 l2 = if last l1 == last l2 then myIsSuffixOf (init l1) (init l2)
                     else False

-- Questão 18 --
myIsSubsequenceOf :: Eq a => [a] -> [a] -> Bool
myIsSubsequenceOf [] l = True
myIsSubsequenceOf l [] = False
myIsSubsequenceOf (x:xs) (y:ys) = if x == y then myIsSubsequenceOf xs ys
                                  else myIsSubsequenceOf (x:xs) ys

-- Questão 19 --
myElemIndices :: Eq a => a -> [a] -> [Int]
myElemIndices b l = aux [] 0 b l

aux :: Eq a => [Int] -> Int -> a -> [a] -> [Int]
aux l n b [] = l
aux l n b (y:ys) = if b == y then aux (l ++ [n]) (n+1) b ys
                   else aux l (n+1) b ys

-- Questão 20 --
myNub :: Eq a => [a] -> [a]
myNub l = nubAux [] l

nubAux :: Eq a => [a] -> [a] -> [a]
nubAux l [] = l
nubAux l (x:xs) = if (elem x l == False) then nubAux (l ++ [x]) xs
                  else nubAux l xs

-- Questão 21 --
myDelete :: Eq a => a -> [a] -> [a]
myDelete x [] = []
myDelete x (y:ys) = if x == y then ys
                    else y : myDelete x ys

-- Questão 22 --
myDeleteList :: Eq a => [a] -> [a] -> [a]
myDeleteList [] l = l
myDeleteList l1 l2 = myDeleteList (tail l2) (myDelete (head l2) l1)

-- Questão 23 --
myUnion :: Eq a => [a] -> [a] -> [a]
myUnion l [] = l
myUnion [] l = l
myUnion l (x:xs) = if elem x l then myUnion l xs
                   else myUnion (l ++ [x]) xs

-- Questão 24 --
myIntersect :: Eq a => [a] -> [a] -> [a]
myIntersect l [] = []
myIntersect [] l = []
myIntersect (x:xs) l = if elem x l then x : myIntersect xs l
                       else myIntersect xs l

-- Questão 25 --
myInsert :: Ord a => a -> [a] -> [a]
myInsert a (x:xs) | a > x = x : myInsert a xs
                  | otherwise = a : x : xs

-- Questão 26 --
myUnwords :: [String] -> String
myUnwords [] = []
myUnwords (x:xs) = x ++ " " ++ myUnwords xs

-- Questão 27 --
myUnlines :: [String] -> String
myUnlines [] = []
myUnlines (x:xs) = x ++ "\n" ++ myUnlines xs

-- Questão 28 --
pMaior :: Ord a => [a] -> Int
pMaior l = maiorAux (head l) 0 0 (tail l)

maiorAux :: Ord a => a -> Int -> Int -> [a] -> Int
maiorAux maior index current [] = index
maiorAux maior index current (x:xs) = if x > maior then maiorAux x current (current+1) xs
                                      else maiorAux maior index (current+1) xs

-- Questão 29 --
temRepetidos :: Eq a => [a] -> Bool
temRepetidos [] = False
temRepetidos (x:xs) = if elem x xs then True
                      else temRepetidos xs

-- Questão 30 --
algarismos :: [Char] -> [Char]
algarismos [] = []
algarismos (x:xs) = if isDigit x then x : algarismos xs
                    else algarismos xs

-- Questão 31 --
posImpares :: [a] -> [a]
posImpares [] = []
posImpares [x] = []
posImpares (x:y:xs) = y : posImpares xs

-- Questão 32 --
posPares :: [a] -> [a]
posPares [] = []
posPares [x] = [x]
posPares (x:y:xs) = x : posPares xs

-- Questão 33 --
myIsSorted :: Ord a => [a] -> Bool
myIsSorted [] = True
myIsSorted (x:y:xs) | x <= y = myIsSorted (y:xs)
                    | otherwise = False

-- Questão 34 --
myInsertionSort :: Ord a => [a] -> [a]
myInsertionSort [] = []
myInsertionSort (x:xs) = insert x (myInsertionSort xs)

-- Questão 35 --
menor :: String -> String -> Bool
menor a b | length a <= length b = True
          | otherwise = False

-- Questão 36 --
elemMSet :: Eq a => a -> [(a, Int)] -> Bool
elemMSet a [] = False
elemMSet a (x:xs) | a == fst x = True
                  | otherwise = elemMSet a xs

-- Questão 37 --
lengthMSet :: [(a, Int)] -> Int
lengthMSet l = sum $ map snd l

-- Questão 38 --
converteMSet :: [(a, Int)] -> [a]
converteMSet [] = []
converteMSet (x:xs) = replicate (snd x) (fst x) ++ converteMSet xs

-- Questão 39 --
insereMSet :: Eq a => a ->  [(a, Int)] -> [(a, Int)]
insereMSet a [] = [(a, 1)]
insereMset a (x:xs) = if a == (fst x) then ((fst x, (snd x) + 1) : xs)
                      else x : insereMSet a xs

-- Questão 40 --
removeMSet :: Eq a => a -> [(a, Int)] -> [(a, Int)]
removeMSet a [] = []
removeMSet a (x:xs) = if a == (fst x) then (((fst x), (snd x) - 1) : xs)
                      else x : removeMSet a xs

-- Questão 41 --
constroiMSet :: Ord a => [a] -> [(a, Int)]
constroiMSet [] = []
constroiMSet (x:xs) = insereMSet x (constroiMSet xs)

-- Questão 42 --
partitionEithers :: [Either a b] -> ([a], [b])
partitionEithers []  = ([], [])
partitionEither l = (left l, right l)
          where left (Left a : t) = a : left t
                left (Right a : t) = left l
                left _ = []
                right (Left a : t) = right t
                right (Right a : t) = a : right t
                right _ = []

-- Questão 43 --
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Just a : xs) = a : catMaybes xs
catMaybes (Nothing : xs) = catMaybes xs

-- Questão 44 --
data Movimento = Norte
               | Sul
               | Este
               | Oeste
               deriving Show

posicao :: (Int, Int) -> [Movimento] -> (Int, Int)
posicao (x, y) [] = (x, y)
posicao (x, y) (Este:xs) = posicao (x+1, y) xs
posicao (x, y) (Oeste:xs) = posicao (x-1, y) xs
posicao (x, y) (Norte:xs) = posicao (x, y+1) xs
posicao (x, y) (Sul:xs) = posicao (x, y-1) xs

-- Questão 45 --
caminho :: (Int, Int) -> (Int, Int) -> [Movimento]
caminho (x, y) (x2, y2) | x < x2 = Este : caminho (x+1, y) (x2, y2)
                        | x > x2 = Oeste : caminho (x-1, y) (x2, y2)
                        | y < y2 = Norte : caminho (x, y+1) (x2, y2)
                        | y > y2 = Sul : caminho (x, y-1) (x2, y2)
                        | otherwise = []

-- Questão 46 --
data Posicao = Pos Int Int
               deriving Show

vizinhos :: Posicao -> [Posicao] -> [Posicao]
vizinhos p [] = []
vizinhos p (x:xs) = if checkNeighbour p x then x : vizinhos p xs
                    else vizinhos p xs

checkNeighbour :: Posicao -> Posicao -> Bool
checkNeighbour (Pos x y) (Pos x2 y2) = if distance == 1 then True else False
                  where distance = sqrt (fromIntegral (x - x2)^2 + fromIntegral (y - y2)^2)

-- Questão 47 --
maisCentral :: [Posicao] -> Posicao
maisCentral [x] = x
maisCentral (x:y:t) | aux x <= aux y = maisCentral (x:t)
                    | aux x >  aux y = maisCentral (y:t)
                  where
                    aux (Pos k m) = sqrt(fromIntegral(k^2+m^2))
