x = [1.5 2.0 2.2 3.0 3.8 4.0]

f = [4.9 3.3 3.0 2.0 1.75 1.5]

p2 = polyfit(x, f, 2);

p2val = polyval(p2, 2.8);

disp("Valor do polinómio de grau 2 em 2.8");
disp(p2val);

disp("O polinómio");
disp(p2);
% 0.67998x^3 + 4.98823x^2 + 10.73276

spline_value = spline(x, [-2 f 3], 2.5);
disp(spline_value);

% -- c)
%  2.547101932475221

% -- d)
disp("Splaine");
splaine = spline(x, f);
%  0.71758x^3 - 0.33194x^2 -1.44370x  - 3.00000