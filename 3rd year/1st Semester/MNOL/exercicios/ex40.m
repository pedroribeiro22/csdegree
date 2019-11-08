% Espaçamentos
h1 = 0.1;
h2 = 3;
h3 = 0.2;

% Tabela dos x's
x = [0:h1:0.6, 3.6:h2:9.6, 9.8:h3:10];

y = [0:h1:0.6, 0.6, 0.6, 0.6, 0.7, 0.8]

area = trapz(x, y); % calcula o integral

disp(area);

% podemos usar `quad('funcao0, limite_inferior, limite_superior, tol [opcional]);`