package abd;

import java.util.Random;

public class Handy {

    public static String randomString(final int size) {

        int ascii_lower_bound = 97;
        int ascii_upper_bound = 122;
        Random rand = new Random();
        return rand.ints(ascii_lower_bound, ascii_upper_bound + 1)
                    .limit(size)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

    public static String stringIt(final String input) {

        return "\'" + input + "\'";
    }

}
