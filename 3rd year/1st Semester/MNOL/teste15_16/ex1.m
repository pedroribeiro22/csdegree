a = [1 3 4 7 9 10 11]

b = [8 10 10 13 18 20 26]


% a)

p = polyfit(a(4:7), b(4:7), 3);
p2 = polyfit(a, b, 3);

% b)
v = polyval(p, 8)
