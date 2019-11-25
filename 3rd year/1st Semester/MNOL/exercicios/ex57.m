%% Introduzir argumentos
x = [1.5 2.0 3.0 4.0];

f = [4.9 3.3 2.0 1.5];

%% a) Calcule a reta

[p1, r1] = polyfit(x, f, 1);

S_p1 = r1.normr ^ 2;

[p2, r2] = polyfit(x, f, 2);

S_p2 = r2.normr ^ 2;

p1_25 = polyval(p1, 2.5);

p2_25 = polyval(p2, 2.5);

% Polinómio interpolador

[P_interpolador, r3] = polyfit(x, f, 3);

S_interpolador = r3.normr ^ 2;

%% b) Calcule o modelo M(x)

% function[M] = ex57b(c, x)
%   M = c(1) ./ x + c(2) .* x;
% end

[c, S] = lsqcurvefit('ex57b', [1 1], x, f);

% [1 1] -> aproximação inicial. Como não tínhamos informação sobre isto no
% enunciado, assumimos um vetor com 1's com o tamanho do número de
% variáveis a descobrir.

% M(x) = (7.4054 / x) - 0.1175 * x; 

% O p2 é melhor, uma vez que tem o resíduo mais pequeno

%% c) Qual dos métodos escolheria? Justifique a escolha

%%
clear all;