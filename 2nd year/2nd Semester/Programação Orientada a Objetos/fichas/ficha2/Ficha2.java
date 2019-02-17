import java.util.Scanner;

public class Ficha2 {

    // Exercício 1
    public void printArray() {
        int[] lista = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int i = 0; i < 10; i++) {
            System.out.println("O elemento número " + i + " é: " + lista[i]);
        }
    }

    // Exercício 2
    private int[] lerArrayInt(int n) {
        int[] lista = new int[n];
        for(int i = 0; i < n; i++) {
            System.out.println("Introduza o número que deseja incluir no array:");
            Scanner scan = new Scanner(System.in);
            lista[i] = scan.nextInt();
        }
        return lista;
    }

    private int minPos(int[] arr) {
        int i = 0;
        int minimo = 100000000;
        for(int runner = 0; i < arr.length; i++) {
            if (arr[runner] < minimo) {
                minimo = arr[i];
                i = runner;
            }
        }
        return i;
    }

    public void merge(int n) {
        int[] lista = lerArrayInt(n);
        int min =  minPos(lista);
        System.out.println(min);
    }

    // Exercício 3
}
