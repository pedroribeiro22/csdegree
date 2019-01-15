-- Exercício 2 --
-- a --
dobros :: [Float] -> [Float]
dobros l = map (* 2) l

-- b--
numOcorre :: Char -> String -> Int
numOcorre b l = length $ filter (== b) l

-- c --
positivos :: [Int] -> Bool
positivos l = if (length a) == (length l) then True
              else False
            where a = filter (>= 0) l

-- d --
soPos :: [Int] -> [Int]
soPos l = filter (>= 0) l

-- e --
somaNeg :: [Int] -> Int
somaNeg l = sum $ filter (< 0) l
