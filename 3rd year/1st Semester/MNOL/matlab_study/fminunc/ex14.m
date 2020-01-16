teta = pi / 6;
n = 5;
m = 10;

aux = 1:2:n;
x(aux) = 2;
aux = 2:2:n;
x(aux) = 1;
i = 1:2:n-1;
y(i) = x(i) * cos(teta) - x(i+1) * sin(teta);
i = 2:2:n-1;
y(i) = x(i) * sin(teta) + x(i+1) * cos(teta);
y(n) = x(n);

[x, fval, exitflag, output] = fminunc('func14', x, [], y, teta, m)