format long;

x0 = [1.5 0.5]

op = optimset('TolFun', 10^-5, 'TolX', 10^-5)

[x, fval, exitflag, output] = fsolve('ex4', x0, op)