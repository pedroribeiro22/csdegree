import java.util.Arrays;
import java.util.Scanner;

public class Ficha2 {

    // Exercício 1
    // a
    static void lerArray() {
        int ceiling = 100;
        int input = 1;
        int i = 0;
        int[] list = new int[ceiling];
        while(input!= 0) {
            System.out.println("Introduz o número que queres no índice " + i + " do array.");
            Scanner scan = new Scanner(System.in);
            input = scan.nextInt();
            list[i] = input;
            i++;
        }
        // PRINT ARRAY
        for(int j = 0; j < i; j++) {
            System.out.println("O indíce número " + j + "tem o valor de: " + list[j]);
        }
    }

    // b
    public int[] arrayEntre(int[] array, int i, int f) {
        int size = f - i + 1;
        int[] res = new int[size];
        System.arraycopy(array, i, res, 0, size);
        return res;
    }

    // Exercício 2
}
