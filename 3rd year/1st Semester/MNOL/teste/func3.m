function [F] = func3(x, a)
    F = -(-10 * abs( (sin(sqrt(x(1)^2 + x(2)^2))) / (sqrt(x(1)^2 + x(2)^2)) ) + sum(a));
end