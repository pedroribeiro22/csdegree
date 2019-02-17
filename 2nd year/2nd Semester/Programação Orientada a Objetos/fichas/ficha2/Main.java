import java.util.Scanner;

public class Main {

    // Função Main
    public static void main(String args[]) {
        Ficha2 f = new Ficha2();
        f.merge(10);
    }

    // Executar exercício 1
    public static void executeFillArray() {
      Ficha2 f = new Ficha2();
      System.out.println("Irá ser impresso no seu ecrã um array com 10 elementos.");
      f.printArray();
    }

    // Executar exercício 2
    public static void executeInfoArray() {
      Ficha2 f = new Ficha2();
      int tamanho;
      System.out.println("Introduza qual o tamanho que prentende para o array:");
      Scanner novo = new Scanner(System.in);
      tamanho = novo.nextInt();
      int[] ar = new int[tamanho];
      ar = lerArrayInt(tamanho);
      int min;
      min =
    }
}
