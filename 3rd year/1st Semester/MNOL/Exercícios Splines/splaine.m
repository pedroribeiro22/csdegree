x = [5.0 5.1 5.2 5.3 5.4 5.5 5.6 5.7 5.8 5.9 6.0]

f = [0.0639  0.0800  0.0988  0.1203  0.1442  0.1714  0.2010  0.2331  0.2673  0.3036  0.3414]

% Fazer a spline
s3 = spline(x, f)

% Calcular o valor da spline num ponto
val = spline(x, f, 5.45)

% Derivadas nos extremos
d0 = (f(1) - f(2)) / (x(1) - x(2))

dn = (f(11) - f(10)) - (x(11) - x(10))

% Spline completa
s3completa = spline(x([1,3:9, 11]), [d0 f([1, 3:9, 11]) dn])

% A spline é colocativa, então para todos os pontos de x correspondem os pontos 
% de f
colocativa = spline(x, f, x)