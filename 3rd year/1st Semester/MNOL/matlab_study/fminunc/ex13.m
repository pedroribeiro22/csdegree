aproximacao = [6, 7, 5];

op = optimset('TolX', 0.0001, 'TolFun', 0.0001);

[x, value, exitflag, output] = fminunc('func13', aproximacao, op)