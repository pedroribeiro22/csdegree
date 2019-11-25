
%% Métodos Numéricos e Otimização não Linear                              %
%  Exercícios Práticos                                         2019/2020  %
%=========================================================================%

%% 62)

% Dados
delta = 2;
M = 0.05;
epsilon = 0.1;

% Função a minimizar
f = @(x) - exp(0.4 * x - 0.01 * x^2); 
x1 = 30;
f_x1 = f(x1);
x2 = x1 + delta;
f_x2 = f(x2); % como o valor de f(x2) aumentou relativamente ao de f(x1) 
              % então temos de caminhar para o lado contrário, uma vez que
              %queremos miniimizar.
              
x_m1 = x1 - delta;
f_xm1 = f(x_m1);
x_m2 = x_m1 - 2 * delta;
f_xm2 = f(x_m2);
x_m3 = x_m2 - 4 * delta;
f_xm3 = f(x_m3);
x_m4 = x_m3 - 4 * delta;
f_xm4 = f(x_m4);
x_m = 16 / 2;
f_m = f(x_m);

%% 64)

% Dados
delta = 1;
M = 0.5;
epsilon = 0.5;
x1 = 5;

% Função a minimizar
f = @(x) - x * (60 - 2*x)^2;

% 1ª iteração
f_x1 = f(x1);
x2 = x1 + delta;
f_x2 = f(x2);
x3 = x2 + 2 * delta;
f_x3 = f(x3);
x4 = x3 + 4 * delta;
f_x4 = f(x4);
x5 = x4 + 8 * delta;
f_x5 = f(x5);
xm = (x4 + x5) / 2;
f_xm = f(xm);

% 2ª iteração
new_x1 = 10.08;
f_new_x1 = f(new_x1);
new_x2 = new_x1 + delta;
f_new_x2 = f(new_x2);
new_x1m = new_x1 - delta;
f_new_x1m = f(new_x1m);
new_x2m = new_x1m - 2 * delta;
f_new_x2m = f(new_x2m);

%%
clear all;




