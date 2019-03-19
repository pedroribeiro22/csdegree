module Ficha1 where
import Data.Char

-- Exercício 1
-- a
perimetro :: Double -> Double
perimetro r = 2 * pi * r

-- b
distancia :: (Double, Double) -> (Double, Double) -> Double
distancia (x, y) (x2, y2) = sqrt $ (x - x2) ^ 2 + (y - y2) ^ 2

-- c
primUlt ::[a] -> (a, a)
primUlt l = (head l, last l)

-- d
multiplo :: Int -> Int -> Bool
multiplo m n = mod m n == 0

-- e
truncaImpar :: [Int] -> [Int]
truncaImpar [] = []
truncaImpar (x:xs) = if even x then x : truncaImpar xs
                     else truncaImpar xs

-- f
max2 :: Int -> Int -> Int
max2 x y = if x > y then x else y

-- g
max3 :: Int -> Int -> Int -> Int
max3 x y z = max2 (max2 x y) z


-- Exercicio 2
-- a
nRaizes :: (Int, Int, Int) -> Int
nRaizes (x, y, z) | a == 0 = 1
                  | a > 0 = 2
                  | otherwise = 0
            where a = y^2 - 4 * x * y

-- b

-- Exercício 3
-- a
type Hora = (Int, Int)

-- a
testaHora :: Hora -> Bool
testaHora (x, y) = (x >= 0) && (x <= 23) && (y >= 0) && (y <= 59)

-- b
isAfter :: Hora -> Hora -> Bool
isAfter (x, y) (x2, y2) | x > x2 = True
                        | y > y2 = True
                        | otherwise = False

-- c
toMinutes :: Hora -> Int
toMinutes (x, y) = x * 60 + y

-- d
toHours :: Int -> Hora
toHours x = (a, x - a * 60)
        where a = div x 60
-- e
minutesGap :: Hora -> Hora -> Int
minutesGap h1@(x, y) h2@(x2, y2) | isAfter h1 h2 = toMinutes h1 - toMinutes h2
                                 | otherwise = toMinutes h2 - toMinutes h1

-- f
correctHour :: Hora -> Hora
correctHour (x, y) | x > 23 = (mod x 23, y + div x 23)
                   | y > 59 = (x + div y 59, mod y 59)

addMinutesToHours :: Hora -> Int -> Hora
addMinutesToHours (x, y) z = correctHour (x + fst a, y + snd a)
        where a = toHours(z)

-- Exercício 5
data Semaforo = Verde
              | Amarelo
              | Vermelho
              deriving (Show, Eq)

-- a
next :: Semaforo -> Semaforo
next Verde = Amarelo
next Amarelo = Vermelho
next Vermelho = Verde

-- b
stop :: Semaforo -> Bool
stop Amarelo = True
stop Vermelho = True
stop Verde = True

-- c
safe :: Semaforo -> Semaforo -> Bool
safe s1 s2 = not (s1 == s2)

-- Exercício 6
data Ponto = Cartesiano Double Double
           | Polar Double Double
           deriving (Show, Eq)

-- a
posx :: Ponto -> Double
posx (Cartesiano x y) = x
posx (Polar r alpha) = r * cos alpha

-- b
posy :: Ponto -> Double
posy (Cartesiano x y) = y
posy (Polar r alpha) = r * sin alpha

-- c
raio :: Ponto -> Double
raio (Cartesiano x y) = sqrt (x^2 + y^2)
raio (Polar r aplha) = r

-- d
angulo :: Ponto  -> Double
angulo (Cartesiano x y) = atan (y / x)
angulo (Polar r alpha) = alpha

-- e
dist :: Ponto -> Ponto -> Double
dist p1 p2 = sqrt $ (posx p1 - posx p2) ^ 2 + (posy p1 - posy p2)

-- Exercicio 7
data Figura = Circulo Ponto Double
            | Rectangulo Ponto Ponto
            | Triangulo Ponto Ponto Ponto
            deriving (Show, Eq)

-- a
poligono :: Figura -> Bool
poligono (Circulo _ _) = False
poligono (Rectangulo _ _) = True
poligono (Triangulo _ _ _) = True

-- b
vertices :: Figura -> [Ponto]
vertices (Circulo _ _) = []
vertices (Rectangulo p1 p2) = [p1, p2]
vertices (Triangulo p1 p2 p3) = [p1, p2, p3]

-- c
area :: Figura -> Double
area (Triangulo p1 p2 p3) =
        let a = dist p1 p2
            b = dist p2 p3
            c = dist p3 p1
            s = (a + b + c) / 2
          in sqrt(s * (s - a) * (s - b) * (s - c))
area (Rectangulo p1 p2) = abs (posx p1 - posx p2) * abs (posy p1 - posy p2)
area (Circulo p1 x) = pi * x ^ 2

-- d
newPerimetro :: Figura -> Double
newPerimetro (Triangulo p1 p2 p3) = dist p1 p2
                               + dist p1 p3
                               + dist p2 p3
newPerimetro (Rectangulo p1 p2) = 2 * (posx p1 - posx p2) + 2 * (posy p1 - posy p2)
newPerimetro (Circulo p1 x) = 2 * pi * x

-- Exercicio 8
-- a
myIsLower :: Char -> Bool
myIsLower r = a >= 97 && a <= 122
        where a = ord r

-- b
myIsDigit :: Char -> Bool
myIsDigit r = a >= 48 && a <= 57
        where a = ord r

-- c
myIsAlpha :: Char -> Bool
myIsAlpha r = (a >= 97 && a <= 122) || (a >= 65 && a <= 90)
        where a = ord r

-- d
myToUpper :: Char -> Char
myToUpper r = chr (ord r - 32)

-- e
myIntToDigit :: Int -> Char
myIntToDigit r = chr (r + 48)

-- f
myDigitToInt :: Char -> Int
myDigitToInt r = ord r - 48
