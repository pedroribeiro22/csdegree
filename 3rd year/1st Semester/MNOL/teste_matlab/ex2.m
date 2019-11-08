x = [32 36 40]

y = [5.9 4.8 2.9]

p = polyfit(x, y, 2);

valor = polyval(p, 37)

% spline completa

new_x = [32 40 43 45 53]
new_y = [5.9 2.9 2.06 2.07 3.11]

d0 = (4.8 - 5.9) / (36 - 32)
dn = (3.11 - 2.09) / (53 - 49)

spl = spline(new_x, [d0 new_y dn])

spl_value = spline(new_x, [d0 new_y dn], 46)
