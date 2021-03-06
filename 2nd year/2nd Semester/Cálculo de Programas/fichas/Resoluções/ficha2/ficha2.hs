module Ficha2 where
import CP

-- -- Exercício 4 --
-- xor :: Bool -> Bool -> Bool
-- xor p1 p2 = p1 /= p2

-- f :: ((Bool, Bool), Bool) -> Bool
-- f = (uncurry xor) . (uncurry (&&) >< id)

xor :: (Bool, Bool) -> Bool
xor (b1, b2) = b1 /= b2

f :: ((Bool, Bool), Bool) -> Bool
f = xor . (and >< id)

-- Não pergunta mas é relativamente fácil definir a função g
g :: ((Bool, Bool), Bool) -> ((Bool, Bool), Bool)
g = split p1 f

