x = 0:0.2:1.4;
f = [1.000, 1.221, 1.492, 1.882, 2.226, 2.718, 3.320, 4.056];

% Alínea a)
[p, s] = polyfit(x, f, 3);

% Alínea b)
val = polyval(p, 0.5);

% Alínea c)
S = s.normr ^ 2;