function [F] = func2a(x)
    F = 2 * x(1)^3 + x(2)^2 - 0.3 * cos(3 * pi * x(1)) + 0.4 * cos(4 * pi * x(2)) + 0.5;
end