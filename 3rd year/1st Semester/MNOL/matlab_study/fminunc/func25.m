function [F] = func25(x, omega)
    v(1) = x(1)^2 + x(2)^2;
    v(2) = x(1)^2 + x(2)^2 + omega * (-4 * x(1) - x(2) + 4);
    v(3) = x(1)^2 + x(2)^2 + omega * (-x(1) - 2 * x(2) + 6);
    F = max(v)
end