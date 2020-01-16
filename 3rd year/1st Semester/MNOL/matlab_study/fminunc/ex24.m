n = 2;

i = 1:n;

myX = i - ((n/2) + 0.5)

myOp = optimset('display', 'iter')

[x, fval, exitflag, output] = fminsearch('func24', myX, myOp)