x = [0, 3, 6, 10, 11, 15]

f = [6.7, 8.2, 9.5, 12.0, 14.9, 18.5]

[C, RESNORM, RESIDUAL, EXITFLAG, OUTPUT] = lsqcurvefit('func1', [5 5], x, f)