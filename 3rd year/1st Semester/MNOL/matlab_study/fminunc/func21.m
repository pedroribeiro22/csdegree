function [F] = func21(x)
    F = max(abs(x(1)), abs(x(2)-1));
end