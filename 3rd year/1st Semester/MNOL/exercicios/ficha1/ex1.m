% --------------------
% |--Exercício 1.1---|
% --------------------

% Vetor u
u = [1 2 3];

% Vetor v
v = [1; 2; 3];

% Vetor linha com os números naturais menores ou iguais a 0
v2 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

v3 = [0:2:12];

% Matriz A
A = [2 2 3; 4 5 6; 7 8 9];


% --------------------
% |--Exercício 1.2---|
% --------------------


% Linhas 2 a 3 e Colunas 1 a 2 de A
B = A(2:3, 1:2);

% Todas as Linhas e Colunas 1 a 2 de A
C = A(:, 1:2);

% Matriz A com mais uma linha: [4 4 4]
D = [A; 4 4 4];

% Linhas 2 e 4 e todas as colunas de D
E = D([2 4], :);

% Fazer `F` na shell
F = [0:3:9; 2:2:8; 5:5:20];


% --------------------
% |--Exercício 1.3---|
% --------------------


% Matriz identidade de 5 x 5
Id5 = eye(5);

% Matriz 3 x 3 com elementos aleatórios entre 0 e 1
rand_between_1_0 = rand(3);

% Matriz 4 x 3 com elementos aleatórios entre -1 e 1
rand_between_1_1 = 2 * rand(4, 3) - 1;

% Matriz nula 2 x 3
zeroed = zeros(3);

% Matriz 2 x 2 com tudo a 1's
oned = ones(2);

% Matriz 10 x 10 com todos os elementos iguais a 10
tens = 10 * ones(10);

% Com os elementos da diagonal de A e os resto a zeros
diagA = diag (diag (A));


% --------------------
% |--Exercício 1.4---|
% --------------------

% Nova matriz A
newA = [1 3 5; 0 4 1; 2 2 1];

newB = ones(3);

vecA = [1 2 1];

vecB = [0 3 5];

% -- alínea a --
a_plus_b = newA + newB;

% -- alínea b --
a_times_b = newA * newB;

% -- alínea c --
vecA_each_product_vecB = vecA .* vecB;

% -- alínea d --
a_each_product_b = newA .* newB;


% --------------------
% |--Exercício 1.5---|
% --------------------


%  function [sum, product] = sumprod(x, y)
%     sum = x + y;
%     product = x * y;
%  end


% --------------------
% |--Exercício 1.6---|
% --------------------


%  function [sum, product] = sumprodn(n)
%     sum = sum(n);
%     product = prod(n);
%  end


% --------------------
% |--Exercício 1.7---|
% --------------------


%  function[max] = max(x, y)
%     max = max(x, y);
%  end