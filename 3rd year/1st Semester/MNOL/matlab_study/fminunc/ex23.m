n = 5;

i = 1:n;
x = i - ((n/2) + 0.5);

op = optimset('display', 'iter', 'TolX', 1e-20)

newOp = optimset('display', 'iter', 'TolX', 1e-20, 'MaxFunEvals', 10000, 'MaxIter', 10000)

[x, fval, exitflag, output] = fminsearch('func23', x, newOp)