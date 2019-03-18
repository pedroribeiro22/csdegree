module Ficha5 where
import CP
import Nat
import List

-- Exercicio 4 --
-- a --
myMult = cataList (either (const 1) mul)

-- b --
g2 (h, l) = l ++ [h]
myReverse = cataList (either nil g2)

-- c --
g3 f (h, l) = f h : l
myMap f = cataList (either nil (g3 f))

-- d --
myListMax = cataList (either zero (uncurry max))
