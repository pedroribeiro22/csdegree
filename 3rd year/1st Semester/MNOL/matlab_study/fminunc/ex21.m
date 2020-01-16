x1 = [1, 1];
op = optimset('display', 'iter')
[x, f, exitflag, output] = fminsearch('func21', x1, op)