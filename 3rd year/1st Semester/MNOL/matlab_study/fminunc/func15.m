function [F] = func15(x)
    i = length(x)
    F = 1 + 1/4000 * sum(x.^2) - prod(cos(x./sqrt(i)))
end