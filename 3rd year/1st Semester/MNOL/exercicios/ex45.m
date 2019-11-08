h = 0.125;

% vetor de x's
x = 0:h:1;

% multiplicar todos os elementos de x pela função
y = x.^2 + 1./(x+1);


simpson = (h / 3) * (y(1) + 4 * y(2) + 2 * y(3) + 4 * y(4) + 2 * y(5) + 4 * y(6) + 2 * y(7) + 4 * y(8) + y(9));

% This is cooler
simpson2 = quad('x.^2 + 1./(x+1)',0, 1, 0.0005);

disp(simpson);

disp(simpson2);