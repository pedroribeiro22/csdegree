function [f] = epistatic(x, t, m)
    n = length(x);
    i = 1:2:n-1;
    y(i) = x(i) * cos(t) - x(i+1) * sin(t);
    i = 2:2:n-1;
    y(i) = x(i) * sin(t) + x(i+1) * cos(t);
    y(n) = x(n);
    i = 1 : n;
    f = -sum(sin(y) .* (sin(i .*y.^2/pi)).^(2*m));
end
