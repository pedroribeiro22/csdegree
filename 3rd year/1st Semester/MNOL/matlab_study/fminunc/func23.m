function [F] = func23(x)
    n = length(x);
    F = n * max(x) - sum(x)
end