format long;

x0 = [0.04]

op = optimset('TolX', 10^1, 'display', 'iter');

[x, fval, exitflag, output] = fsolve('ex2', x0, op);

disp("CONVERGIU? .............");
disp(exitflag);

disp("QUANTAS ITERAÇÕES? .....");
disp(output);

disp("APROXIMAÇÃO DA SOLUÇÃO? ..");
disp(x);

% Convergiu, uma vez que a exitflag é 1.
% 0 iterações (weird)
% Aproximação da solução: 0.041000000000
