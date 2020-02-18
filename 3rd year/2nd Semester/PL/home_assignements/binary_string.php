<?php

$pattern = "/0*1((0*1){2})*0+/";
$correct_pattern = "/(00|11|(01|10)(00|11)*(10|01))*(1|(01|10)(11|00)*0)/";
$another_pattern = "/(0*0*1)((0*1){2})*0*0*/";
$text_array = array("10101", "111110", "1110011");


foreach ($text_array as $linha) {

    foreach (count_chars($linha, 1) as $i => $val) {
         echo "\"" , chr($i) , "\": $val";
         if($val % 2 == 0) echo " -> PAR\n";
         else echo " -> ÍMPAR\n";
    }

    if(preg_match($correct_pattern, $linha)) {

        echo "Match found in: $linha\n";

    } else {

        echo "Match not found in: $linha\n";

    }
}

?>