format long;

op = optimset('TolFun', 10^-10);

integral = quad('ex3', 0.1, 1, 10^-10);