ex1:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex2:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl


ex3:
	flex ex3.l
	gcc -o ex3.exe lex.yy.c -lfl

ex4:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex5:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex6:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex7:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex8:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex9:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex9:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex10:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex11:
	flex ex1.l
	gcc -o ex1.exe lex.yy.c -lfl

ex12:
	flex ex12.l
	gcc -o ex12.exe lex.yy.c -lfl

ex13:
	flex ex13.l
	gcc -o ex13.exe lex.yy.c -lfl

ex14:
	flex ex14.l
	gcc -o ex14.exe lex.yy.c -lfl

ex15:
	flex ex15.l
	gcc -o ex15.exe lex.yy.c -lfl

tokenizer: tokenizer.o lex.yy.o
	gcc -o tokenizer tokenizer.o lex.yy.o -lfl

tokenizer.o: tokenizer.c ex8.h
	gcc -c tokenizer.c

lex.yy.o: lex.yy.c ex8.h	
	gcc -c lex.yy.c

lex.yy.c: ex8.l
	flex ex8.l

clean:
	rm *.exe