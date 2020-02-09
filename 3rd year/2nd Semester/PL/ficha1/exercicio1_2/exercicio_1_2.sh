#!/bin/bash

FILE_1="exercicio1_2/ex_1.vm"
FILE_5="exercicio1_2/ex_5.vm"
FILE_7="exercicio1_2/ex_7.vm"
FILE_ARRAY="exercicio1_2/somaArray.vm"

printf "EXERCÍCIO 1.2 -> alínea $1)\n"

case $1 in
    "a")
        # Regex a: PUSH .+
        printf "FICHEIRO: $FILE_1\n"
        grep -P "[Pp][Uu][Ss][Hh].+" $FILE_1

        printf "FICHEIRO: $FILE_5\n"
        grep -P "[Pp][Uu][Ss][Hh].+" $FILE_5

        printf "FICHEIRO: $FILE_7\n"
        grep -P "[Pp][Uu][Ss][Hh].+" $FILE_7

        printf "FICHEIRO: $FILE_ARRAY\n"
        grep -P "[Pp][Uu][Ss][Hh].+" $FILE_ARRAY
        ;;
    "b")
        # Regex b: //.*
        printf "FICHEIRO: $FILE_1\n"
        grep -P "//.*" $FILE_1

        printf "FICHEIRO: $FILE_5\n"
        grep -P "//.*" $FILE_5

        printf "FICHEIRO: $FILE_7\n"
        grep -P "//.*" $FILE_7

        printf "FICHEIRO: $FILE_ARRAY\n"
        grep -P "//.*" $FILE_ARRAY
        ;;
    "c")
        # Regex c: (\d|[a-z])*:.*
        printf "FICHEIRO: $FILE_1\n"
        grep -P "(\d|[a-z])*:.*" $FILE_1

        printf "FICHEIRO: $FILE_5\n"
        grep -P "(\d|[a-z])*:.*" $FILE_5

        printf "FICHEIRO: $FILE_7\n"
        grep -P "(\d|[a-z])*:.*" $FILE_7

        printf "FICHEIRO: $FILE_ARRAY\n"
        grep -P "(\d|[a-z])*:.*" $FILE_ARRAY
        ;;
esac
