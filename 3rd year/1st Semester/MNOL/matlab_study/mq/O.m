function [F] = O(c, x)
    F = c(1) + c(2) .* x + c(3) .* (1 ./ x)
end
