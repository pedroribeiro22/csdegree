function [F] = ex18(x)
    F = 20000 * ((x * (x+1)^6) / ((x+1)^6 - 1)) - 4000
end