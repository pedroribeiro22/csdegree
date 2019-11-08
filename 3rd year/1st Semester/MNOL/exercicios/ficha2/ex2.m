function [F] = ex2(x)
  F(1) = 70 * exp(x(1)) + 20 * exp(x(2)) - 27.5702;
  F(2) = 70 * exp(2 * x(1)) + 20 * exp(2* x(2)) - 17.6567;
endfunction

% op = optimset('TolFun', 10^-8, 'TolX', 10^-10, 'display', 'iter');

% x0 = [-1, -0.1];

% [x, fval, exitflag, output] = fsolve('ex2', x0, op);



% ------------------------ COM DERIVADAS -------------------------------------

% function [F, d] = ex2(x)
%   F(1) = 70 * exp(x(1)) + 20 * exp(x(2)) - 27.5702;
%   F(2) = 70 * exp(2 * x(1)) + 20 * exp(2 * x(2)) - 17.6567;
%   d(1) = 70 * exp(x(1)) + 20 * exp(x(2));
%   d(2) = 140 * exp(x(1)) + 40 * exp(2 * x(2));
% endfunction

% op = optimset('TolFun', 10^-8, 'TolX', 10^-10, 'display', 'iter', 'Jacobian, 'on');

% x0 = [-1, -0.1];

% [x, fval, exitflag, output] = fsolve('ex2', x0, op); 