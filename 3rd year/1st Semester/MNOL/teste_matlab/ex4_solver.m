x0 = [-0.5 -0.5]

op = optimset('TolX', 10^-10, 'TolX', 10^-10);

[x, fval, exitflag, output] = fsolve('ex4', x0, op);