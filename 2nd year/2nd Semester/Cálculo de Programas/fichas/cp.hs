module Cp where

-- (1) Product --------
split :: (a->b) -> (a->c) -> a -> (b,c)
split f g x = (f x, g x)

(><) :: (a->b) -> (c->d) -> (a,c) -> (b,d)
f >< g = split (f . p1) (g . p2)

p1 = fst
p2 = snd



