%% Métodos Numéricos e Otimização não Linear - Exercícios Otimização      %
%  Exercícios Práticos                                         2019/2020  %
%=========================================================================%

%% Exercício 1.1)

%% ---------- aaa ----------)

clear all;

% Função do enunciado
function_1 = @(x) 0.25 * x(1).^4 - 0.5 * x(1).^2 + 0.1 * x(1) + 0.5 * x(2).^2;

% Aproximação inicial
x_i = [-1 0.5];

[x, f, exitflag, output] = fminunc(@function_1, x_i);

% ver os valores por defeito que o matlab tem para o rotina `fminunc`
optimset fminunc

%% ---------- bbb ----------)

x_i = [1 1];

clear all;

op = optimset('gradobj', 'on');

[x, f, exitflag, output] = fminunc(@function_1, x_i, op);

%% Exercício 1.2)

clear all;

x_i = [1 1];

a = [3 4 1];

b = [1.2 1.5 1];

function_2 = @(x) -((a(1) * (1 - exp(-b(1) * x(1)))) + (a(2) * (1 - exp(-b(2) * x(2)))) + (a(3) * (1- exp(-b(3) * x(1) * x(2)))) - x(1) - x(2));

[x, f, exitflag, output] = fminunc(@function_2, x_i);


%% Exercício 1.4)
clear all;

n = 10;
i = 1:2:n; % indicies impares
x1(i) = 2;
i = 2:2:n; % indices pares
x1(i) = 1;
t = pi / 6;
m = 10;
% da exitflag 0, nao converge
op = optimset('maxiter', 5);
[x, f, exitflag, output] = fminunc('epistatic',x1,[],t,m)

%% Exercício 1.5)
n = 10;
x1 = ones(1, n);
op = optimset('Hessupdate', 'DFP');
[x, f, exitflag, output] = fminunc('griewank', x1, op)