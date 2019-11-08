format long;

x0 = 5

op = optimset('TolFun', 10^-12, 'TolX', 10^-12);

[x, fval, exitflag, output] = fsolve('ex5', x0, op);

x
