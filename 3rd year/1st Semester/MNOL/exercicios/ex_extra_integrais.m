% I = integral [entre 1 e 2] de (1 / x)

% -- a)

integral = quad(@(x) 1./(x), 1, 2);

disp(integral);

% -- b) calcular o integral usando 6 pontos
% -- h = (b - a) / n => n >= 1/5;

x = [1:1/5:2]

y = 1./x;

newIntegral = trapz(x, y);

display(newIntegral);
