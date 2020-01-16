x1 = [1, -0.1]

x2 = [2, 2]

x3 = [-10, -10]

op = optimset('display', 'iter');

[x, fval, exitflag, output] = fminsearch('func22', x1, op)