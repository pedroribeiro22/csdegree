# Grafos

# Em prolog existem 3 maneiras de representar grafos:

aresta - ligação entre 2 nodos num grafo não orientado arco - ligação entre 2
nodos num grafos orientado

## 1ª maneira: factos

// Num grafo não orientado aresta( a,b ).  aresta( c,f ).


grafo([a,b,c,d,e,f,g], aresta(a,b), aresta(c,d), aresta(c,f), aresta(d,f),
aresta(f,e), aresta(e,d)).
