function [F] = N(c, x)
    F = c(1) .* exp(x) + c(1) .* (1 ./ x)
end
