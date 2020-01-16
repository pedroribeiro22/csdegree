function [F] = func1(c, x)
    F = c(1) .* (x .^ 3) + c(2) .* exp(1 ./ (x+1))
end
