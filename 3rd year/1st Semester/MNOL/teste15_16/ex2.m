xi = [1 2 3 5 7 9]

fi = [1 3 2 12 21 6]

s = spline(xi, fi, 7.5)

s2 = spline(xi, fi)

d0 = (fi(2) - fi(1)) / (xi(2) - xi(1))
dn = (fi(5) - fi(6)) / (xi(5) - xi(6))

estimativa_cubica = spline(xi([1, 3, 4, 6]), [d0, fi([1, 3, 4, 6]), dn], 7.5)