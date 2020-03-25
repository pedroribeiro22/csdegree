listas.exe : y.tab.o lex.yy.o
	gcc -o listas.exe y.tab.o lex.yy.o -lfl

y.tab.o : y.tab.c
	gcc -c y.tab.c

lex.yy.o : lex.yy.c
	gcc -c lex.yy.c

y.tab.c y.tab.h : listas.y
	yacc -d listas.y

lex.yy.c : listas.l y.tab.h 
	flex listas.l