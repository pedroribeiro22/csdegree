i = 1:21;
t = 0.25 + 0.75*(i -1)./20;
x = ones(1, 4);

op = optimset('display', 'iter');

[x, fval, exitflag, output] = fminsearch('func26', x, op, t)