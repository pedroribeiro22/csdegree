function [F] = Q(c, x)
    F = c(1) .* x + c(2) .* exp(x)
end