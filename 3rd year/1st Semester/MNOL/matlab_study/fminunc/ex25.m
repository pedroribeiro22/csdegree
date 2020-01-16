omega = 500;

x = [-1, 5]

op = optimset('display', 'iter')

[x, fval, exitflag, output] = fminsearch('func25', x, op, omega)