alunos.exe: y.tab.o lex.yy.o
	gcc -o alunos.exe y.tab.o lex.yy.o -lfl

y.tab.o: y.tab.c
	gcc -c y.tab.c

lex.yy.o: lex.yy.c
	gcc -c lex.yy.c

y.tab.o y.tab.h: alunos.y
	yacc
