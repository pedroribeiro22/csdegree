aproximacao = [-1, 0.5]

op = optimset('gradobj', 'on');

[x, fval, exitflag, output] = fminunc('func11b', aproximacao, op)