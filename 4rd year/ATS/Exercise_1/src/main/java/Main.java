import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

    }

    public static void exercise_1() {

        int[] values = {3, 6, 8, 7, 9, 5};
        int result = 0;
        for (int v : values) {
            int i = v * v;
            result += i;
        }

    }

    public static void exercise_1_a() {

        int[] values = {3, 6, 8, 7, 9, 5};
        int result = 0;

        for(int i = 0; i < values.length; i++) {
            result += Math.pow(values[i], 2);
        }

    }


}
