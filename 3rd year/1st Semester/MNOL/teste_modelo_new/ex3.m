x2 = [1.5, 2.0, 2.2, 3.0, 3.8, 4.0];

y2 = [4.9, 3.3, 3.0, 2.0, 1.75, 1.5];

p = polyfit(x2, y2, 2);

valor = polyval(p, 2.8);

disp(valor);

% ultima alinea

c = spline(x2, [-2 y2 3], 2.5);

newP = spline(x2, [-2 y2 3]);