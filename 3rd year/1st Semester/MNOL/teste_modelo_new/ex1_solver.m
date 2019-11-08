format long;

x0 = [0, 0.1]

op = optimset('TolX', 10^-2, 'TolFun', 10^-1);

[x, fval, exitflag, output] = fsolve('ex1', x0, op);

disp(x);
disp(fval);
disp(exitflag);
disp(output);