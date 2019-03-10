\documentclass{article}
%include polycode.fmt
\usepackage[utf8]{inputenc}
\usepackage[portuguese]{babel}

\title{Ficha 1 - Cálculo de Programas 2018/2019}
\author{Pedro Alexandre Gonçalves Ribeiro}
\date{7 de Fevereiro de 2019}
\begin{document}
\maketitle
\tableofcontents
\newpage
\section{Resoluções}
\subsection{Questão 1}
\newline
\textbf{Alínea \textit{a}}
\newline
\newline
\textit{Primeiro caso}
\newline
\[(f \cdot g) x \) \equiv \(f (g x)\) \equiv \(f (x + 1)\) \equiv \(2 \times (x + 1)\) \equiv \(2 \times x + 2 \times 1\) \equiv \(2x \times 2\]
\newline
\newline
\textit{Segundo caso}
\newline
\[(f \cdot g) x\) \equiv \(f (g x)\) \equiv \(f(2 \times x)\) \equiv \(succ(2x)\) \equiv 2x + 1\]
\newline
\newline
\textit{Terceiro caso}
\newline
\[(f \cdot g) x \equiv f(g(x,y)) \equiv f(x+y) \equiv succ \cdot (\times 2) (x + y) \equiv (2 \times x + 2 \times y + 1)\]

\subsection{Questão 2}
\textbf{Alínea \textit{a}}
\begin{code}
myLength :: [a] -> Int
myLength = foldl (const . succ) 0
\end{code}
\newline
\textbf{Alínea \textit{b}}
\begin{code}
myReverse :: [a] -> [a]
myReverse [h] = [h]
myReverse l = [last l] ++ myReverse (init l)
\end{code}


\subsection{Questão 3}
\begin{code}
myCatMaybes :: [Maybe a] -> [a]
myCatMaybes [] = []
myCatMaybes (Nothing : xs) = catMaybes xs
myCatMaybes (Just b : xs) = b : catMaybes xs
\end{code}

\subsection{Questão 4}
\textbf{Alínea \textit{a}}
\begin{code}
myUncurry :: (a -> b -> c) -> (a, b) -> c
myUncurry f (x, y) = f x y
\end{code}
\newline
\textbf{Alínea \textit{b}}
\begin{code}
myCurry :: ((a, b) -> c) -> a -> b -> c
myCurry f x y = f (x, y)
\end{code}
\newline
\textbf{Alínea \textit{c}}
\begin{code}
myFlip :: (a -> b -> c) -> b -> a -> c
myFlip f x y = f y x
\end{code}

\subsection{Questão 5}
\begin{code}
data LTree a = Leaf a | Fork (LTree a, LTree a)
\end{code}
\newline
\textbf{Alínea \textit{a}}
\begin{code}
flatten :: LTree a -> [a]
flatten (Leaf b) = [b]
flatten (Fork (e, d)) = flatten e ++ flatten d
\end{code}
\newline
\textbf{Alínea \textit{b}}
\begin{code}
mirror :: LTree a -> LTree a
mirror (Leaf b) = Leaf b
mirror (Fork (e, d)) = Fork (mirror d, mirror e)
\end{code}
\newline
\textbf{Alínea \textit{c}}
\begin{code}
myFmap :: (b -> a) -> LTree b -> LTree a
myFmap f (Leaf i) = Leaf (f i)
myFmap f (Fork (e, d)) = Fork (myFmap f e, myFmap f d)
\end{code}

\subsection{Questão 6}
\begin{code}
newLength :: [a] -> Int
newLength = foldr (\l acc -> 1 + acc) 0
\end{code}

\end{document}














