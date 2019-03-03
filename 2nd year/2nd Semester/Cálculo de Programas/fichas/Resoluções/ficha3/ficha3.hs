module Ficha3 where
import CP

-- exercício 6 --
myCoassocr (Right x) = Right (Right x)
myCoassocr (Left (Right x)) = Right (Left x)
myCoassocr (Left (Left x)) = Left x