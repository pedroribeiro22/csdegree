x = [0.0 0.2 0.4 0.6 0.8 1.0 1.2 1.4]

y = [1.000 1.221 1.492 1.882 2.226 2.718 3.320 4.056]

% a)
p3 = polyfit(x, y, 3);

% p3(1) x^3 + p3(2) x^2 + p3(3) x + p(4)

% aproximação do polinómio no ponto x = 0.5
val = polyval(p3, 0.5);