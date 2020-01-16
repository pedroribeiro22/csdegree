x = [1.2, 1.75, 1.1, 2.0, 0.5, 0.8, 1.0, 1.5];
f = [16, 18, 16, 19, 10, 11, 14, 16];

% Alínea a)
[p, s] = polyfit(x, f, 1);

p

% Alínea b)
S = s.normr ^ 2;

S

% Alínea c)
val = polyval(p, 1.6);

val