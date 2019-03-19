-- Exercício 1 --
data Hora = H Int Int
            deriving Show

type Etapa = (Hora, Hora)
type Viagem = [Etapa]

-- a
validateEtapa :: Etapa -> Bool
validateEtapa (H h1 m1, H h2 m2) | h2 < h1 = False
                                 | h2 == h1 = if m2 > m1 then True
                                              else False
                                 | otherwise = True

-- b
validateViagem :: Viagem -> Bool
validateViagem [] = True
validateViagem (x:xs) = if validateEtapa x then validateViagem xs
                        else False

-- c
arrivalDeparture :: Viagem -> Etapa
arrivalDeparture l = if validateViagem l then (fst (head l), snd (last l))
                     else (H 0 0, H 0 0)

-- d
toMinutes :: Hora -> Int
toMinutes (H h m) = 60 * h + m

travelTime :: Viagem -> Int
travelTime l = toMinutes b - toMinutes a
      where (a, b) = arrivalDeparture l

-- f
totalTime :: Viagem -> Int
totalTime l = toMinutes b - toMinutes a
      where (a, b) = arrivalDeparture l


-- Exercicio 2
type Poligonal = [Ponto]

data Ponto = Cartesiano Double Double
           | Polar Double Double
           deriving (Show, Eq)

-- a
getX :: Ponto -> Double
getX (Cartesiano x y) = x

getY :: Ponto -> Double
getY (Cartesiano x y) = y

castCartesiano :: Ponto -> Ponto
castCartesiano (Cartesiano x y) = Cartesiano x y
castCartesiano (Polar r alpha) = Cartesiano (r * cos (alpha)) (r * sin (alpha))

dist :: Ponto -> Ponto -> Double
dist p1 p2 = sqrt $ (getX a - getX b) ^ 2 + (getY a - getY b) ^ 2
    where a = castCartesiano p1
          b = castCartesiano p2

compPoligonal :: Poligonal -> Double
compPoligonal [x] = 0
compPoligonal (x : y : ys) = dist x y + compPoligonal (y : ys)

-- b
areTheSame :: Ponto -> Ponto -> Bool
areTheSame p1 p2 = a == 0
        where a = dist p1 p2

fechada :: Poligonal -> Bool
fechada l = areTheSame (head l) (last l)

-- Exercicio 3
data Contacto = Casa Integer
             | Trab Integer
             | Tlm  Integer
             | Email String
             deriving Show

type Nome = String
type Agenda = [(Nome, [Contacto])]

