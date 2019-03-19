-- Exercício 1 --
data Hora = H Int Int
            deriving Show

type Etapa = (Hora, Hora)
type Viagem = [Etapa]

validateEtapa :: Etapa -> Bool
validateEtapa (H h1 m1, H h2 m2) | h2 < h1 = False
                                 | h2 == h1 = if m2 > m1 then True
                                              else False
                                 | otherwise = True

validateViagem :: Viagem -> Bool
validateViagem [] = True
validateViagem (x:xs) = if validateEtapa x then validateViagem xs
                        else False

arrivalDeparture :: Viagem -> Etapa
arrivalDeparture l = if validateViagem l then (fst (head l), snd (last l))
                     else (H 0 0, H 0 0)

toMinutes :: Hora -> Int
toMinutes (H h m) = 60 * h + m

travelTime :: Viagem -> Int
travelTime l = toMinutes b - toMinutes a
      where (a, b) = arrivalDeparture l


totalTime :: Viagem -> Int
totalTime l = toMinutes b - toMinutes a
      where (a, b) = arrivalDeparture l


-- Exercício 3 --
data Contacto = Casa Integer
              | Trab Integer
              | Tlm  Integer
              | Email String
              deriving Show

type Nome = String
type Agenda = [(Nome, [Contacto])]

-- a --
acresEmail :: Nome -> String -> Agenda -> Agenda
acresEmail n m [] = [(n, [Email m])]
acresEmail n m ((nome, [contacto]) : xs) = if n == nome then ((nome, [contacto] ++ [Email m])) : xs
                                           else (nome, [contacto]) : acresEmail n m xs

-- b --
verEmails :: Nome -> Agenda -> Maybe [String]
verEmails n [] = Nothing
verEmails n ((nome, [contacto]) : xs) = if n == nome 
