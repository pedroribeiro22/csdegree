<?php

$pattern = "/(10[^1])+$/";
$text_array = array("0001", "00010", "011010101", "101000", "001000");

foreach($text_array as $linha) {

    if(preg_match($pattern, $linha)) {

        echo "A string $linha não contém o padrão $pattern.\n";

    } else {

        echo "$linha: contém.\n";
    }
}
?>