x2 = [3, 3];

op = optimset('gradobj', 'on', 'TolX', 1.0e-8);

[x, fval, exitflag, output] = fminunc('func2b', x2qqq, op)