function [F, d] = func2b(x)
    F = 2 * x(1)^3 + x(2)^2 - 0.3 * cos(3 * pi * x(1)) + 0.4 * cos(4 * pi * x(2)) + 0.5;
    d(1) = 6 * x(1)^2 + 0.9 * pi * sin(3 * pi * x(1));
    d(2) = 2 * x(2) - 1.6 * pi * sin(4 * pi * x(2));
end