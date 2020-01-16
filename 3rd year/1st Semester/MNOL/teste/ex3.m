x3 = [10, 10];

i = 1:1:10;

a = sqrt(i);

op = optimset('TolX', 1.0e-6, 'display', 'iter');

[x, fval, output, exitflag] = fminsearch('func3', x3, op, a);