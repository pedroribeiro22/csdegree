function [F] = M(c, x)
    F = c(1) + c(2) .* cos(x) + c(3) .* sin(x)
end
