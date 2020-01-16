
function [F] = func14(x, y, t, m)
    n = length(x);
    i = 1:n;
    F = -sum(sin(y) .* (sin(i .* y.^2 / pi)).^(2*m));
end
