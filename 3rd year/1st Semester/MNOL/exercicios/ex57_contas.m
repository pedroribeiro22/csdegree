x = [1.5 2.0 3.0 4.0];

f = [4.9 3.3 2.0 1.5];

b0 = sum(x) / 4;

p1 = x - b0;

c1 = sum( f .* p1);

p1quadrado =  p1 .^ 2;

f_times_p1 = f .* p1;

sum_p1_squared = sum(p1quadrado);

sum_f_times_p1 = sum(f_times_p1);

x_times_p1_squared = x .* p1quadrado;

soma_x_times_p1quadrado = sum(x_times_p1_squared);

p2 = (x - 2.892) .* (x - 2.625) - 0.9219;

p2_squared = p2 .^ 2;

f_times_p2 = f .* p2;

sum_f_times_p2 = sum(f_times_p2);

inverse_x_squared = (1 ./ x) .^ 2;

x_squared = x .^ 2;

f_times_inverse_x = f .* (1 ./ x);

f_times_x = f .* x;

sum_inverse_x_squared = sum(inverse_x_squared);

sum_f_times_inverse_x = sum(f_times_inverse_x);

sum_x_squared = sum(x_squared);

sum_f_times_x = sum(f_times_x);

A = [0.8681 4; 4 31.25];

b = [5.9583; 25.95];

resultado = A \ b;

p1_aproximador = (x - 2.625) .* (-1.2915) + 2.925;

p2_aproximador = p1_aproximador + 0.6479 .* ((x - 2.892) .* (x - 2.625) - 0.9219);

residuo1 = sum((f - p1_aproximador) .^ 2);

residuo2 = sum((f - p2_aproximador) .^ 2);
