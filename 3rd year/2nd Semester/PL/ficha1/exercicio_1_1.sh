#!/bin/bash

FILE="exercicio1_1/index.html"

printf "EXERCÍCIO 1.1 -> alínea $1)\n"

case $1 in
    "a")
        ## Regex a: [Hh][Tt][Mm][Ll]
        grep -P "[Hh][Tt][Mm][Ll]" $FILE
        ;;
    "b")
        # Regex b: </*[Hh][Tt][Mm][Ll]>
        grep -P "</*[Hh][Tt][Mm][Ll]>" $FILE
        ;;

    "c")
        # Regex c: <[Hh]\d
        grep -P "<[Hh]\d>" $FILE
        ;;

    "d")
        # Regex d: <A
        grep -P "<A" $FILE
        ;;

    "e")
        # Regex e: .*\d\d+.*| .*\d\d+
        grep -P ".*\d\d+ .*| .*\d\d+" $FILE
        ;;

    "f")
        # Regex e: <!--.*-->
        grep -P "<!--.*-->" $FILE
        ;;
esac
