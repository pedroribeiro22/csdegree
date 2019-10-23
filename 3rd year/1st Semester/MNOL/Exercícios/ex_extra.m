format short

x = [-5 -3 0 1 1.25 4 6]

f = [20 10 -12 -4 7 10 9]

% Queremos calcular p3(0.5) e p4(-1)

% Para calcular p3 temos de escolher 4 pontos, escolhemos os 2 pontos em volta
% x = 0, x = 1 e depois os mais próximo à volta: x = 1.25 e x = 3

p3 = polyfit(x(2:5), f(2:5), 3)

p3val = polyval(p3, 0.5)

% Para calcular p4 temos de escolher 5 pontos, escolhemos os 2 pontos em volta
% x = -3, x = 0, x = 1, x = 1.25, x = -5.

p4 = polyfit(x(1:5), f(1:5), 4)

p4val = polyval(p4, -1)
