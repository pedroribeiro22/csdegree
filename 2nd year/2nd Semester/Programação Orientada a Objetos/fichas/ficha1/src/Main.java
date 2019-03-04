import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Exercicios ficha1 = new Exercicios();
        System.out.println("Introduza: ");
        Scanner scan = new Scanner(System.in);
        double celsius = scan.nextDouble();
        double farenheit = ficha1.celsiusToFarenheit(celsius);
        System.out.println("Em Farenheit é: " + farenheit);
    }
}
