format long;

x0 = [0, 0.1]

op = optimset('TolX', 10^-2, 'TolFun', 10^-1, 'display', 'iter');

[x, fval, exitflag, output] = fsolve('ex1', x0, op);

disp(x);
disp(fval);
disp(exitflag);
disp(output);

% -- a)
% o valor de [x1, x2] = [0.2028684405, 0.1149731059]

% -- b)
% o método precisou de 3 iterações
