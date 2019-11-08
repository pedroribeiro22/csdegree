xi = [(0:0.1:0.6) (3.6:3:9.6) 9.8 10]

fi = [(0:0.1:0.6) 0.6 0.6 0.6 0.7 0.8]

cubica_value = spline(xi, fi, 5)

d0 = (fi(1) - fi(2)) / (xi(1) - xi(2))
dn = (fi(12) - fi(11)) / (xi(12) - xi(11))

completa_value = spline(xi([1, 3:10, 12]), [d0 fi([1, 3:10, 12]) dn], 5) 