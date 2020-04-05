listas: listas.y listas.fl
	yacc listas.y
	flex listas.fl
	gcc -o listas y.tab.c

clean:
	rm -f y.tab.c lex.yy.c listas
