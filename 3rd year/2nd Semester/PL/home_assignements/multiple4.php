<?php

$pattern = "/(10)+00$/";
$text_array = array("0001", "00010", "011010101", "101000", "001000");

foreach($text_array as $linha) {

    if(preg_match($pattern, $linha)) {

        echo "O número é múltiplo de 4!\n";

    } else {

        echo "O número não é múltiplo de 4!\n";
    }
}
?>