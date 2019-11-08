function [F] = ex1(x)
  F(1) = -5*x(1) + 3*sin(x(1)) + cos(x(2));
  F(2) = 4*cos(x(1)) + 2*sin(x(2)) -5*x(2);
endfunction
