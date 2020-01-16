x = [-1.00, -0.95, -0.85, -0.80, 0.20, 0.50, 0.90]

f = [-1.00, -0.05, 0.90, 1.00, 0.90, 0.50, -0.30]

% Alínea a)
% M
[C, RESNORM, RESIDUAL, EXITFLAG, OUTPUT] = lsqcurvefit('M', [1 1 1], x, f)

% N
[C2, RESNOMR2, RESIDUAL2, EXITFLAG2, OUTPUT2] = lsqcurvefit('N', [1], x, f)

% O
[C3, RESNORM3, RESIDUAL3, EXITFLAG3, OUTPUT3] = lsqcurvefit('O', [1 1 1], x, f)

% Q
[C4, RESNORM4, RESIDUAL4, EXITFLAG4, OUTPUT4] = lsqcurvefit('Q', [1 1], x, f)

% Alínea b)

valueM = polyval(OUTPUT, 0.6);
valueM

valueN = polyval(OUTPUT2, 0.6);
valueN

valueO = polyval(OUTPUT3, 0.6);
valueO

valueQ = polyval(OUTPUT4, 0.6);
valueQ