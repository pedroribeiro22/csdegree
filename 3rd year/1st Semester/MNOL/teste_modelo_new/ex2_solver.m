format long;

x1 = 0.04;

op = optimset('TolX', 10^-1);

[x1, fval, exitflag, output] = fsolve('ex2', x1, op);

disp(x1);
disp(exitflag);
disp(output);