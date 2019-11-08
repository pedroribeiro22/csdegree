function [F] = ex1(x)
  F = (x-1) * exp(x) - x - 23;
endfunction

% op = optimset('TolFun', 10^-9, 'TolX', 10^-10, 'display, 'iter');

% [x, fval, exitflag, output] = fzero('h', 2, op);

% ------------ 1) ---------------
% usei o `fzero` (uma vez que é uma só equação não linear

% ------------ 2) ---------------
% o método convergiu uma vez que a `exitflag = 1` e de acordo com a documenta-
% ção isso sugere que o método convergiu.

% ------------ 3) ---------------
% exigiu 7 iterações

% ------------ 4) ---------------
% x =  2.710250120521


% ------------------------- COM DERIVADAS -------------------------------------

% function [F, d] = ex1(x)
%   F = (x - 1) * exp(x) - x - 23;
%   d = 