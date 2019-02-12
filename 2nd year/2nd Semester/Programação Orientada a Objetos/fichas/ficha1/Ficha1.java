import java.util.Scanner;
import java.util.Date;

public class Ficha1 {

    // Exercicios 2

    // Converte uma temperatura fornecida em graus Celsius para Farenheit
    public double celsiusToFarenheit(double degrees) {
        return (1.8 * degrees) + 32;
    }

    // Calcula o máximo entre 2 inteiros
    public int maximoNumeros(int a, int b) {
        return (a > b ? a : b);
    }

    // Cria uma descrição de conta conforme pedido
    public String criaDescricaoConta(String nome, int saldo) {
        return("Nome: " + nome + "   Saldo: " + saldo);
    }

    // Converte uma quantia de euros para libras
    public double eurosToPounds(double valor, double taxaConversao) {
        return (valor * taxaConversao);
    }

    // Lê 2 inteiros e escreve-os por ordem crescente, assim como a sua media
    public double readnwrite() {
        System.out.println("Introduza o primeiro número: ");
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduza o segundo número: ");
        Scanner scan2 = new Scanner(System.in);
        int num1 = scan.nextInt();
        int num2 = scan2.nextInt();
        double mean = (num1 + num2) / 2;
        if(num1 > num2) {
            System.out.println("O menor número é: " + num2);
            System.out.println("O segundo menor número: " + num1);
        } else {
            System.out.println("O menor número é: " + num1);
            System.out.println("O segundo menor número é: " + num2);
        }
        return mean;
    }

    // Calcula fatorial de um número (a parte do input é tratada no módulo main)
    public long factorial(int num) {
        int multiplier = num - 1;
        while(multiplier > 0) {
            num *= multiplier;
            multiplier--;
        }
        return (long) num;
    }

    public long tempoGasto() {
        Date before = new Date();
        int hours = before.getHours();
        int minutes = before.getMinutes();
        int seconds = before.getSeconds();
        // Calcular o fatorial de 5000
        int fatorial = 1;
        for(int i = 0; i <= 5000; i++)
            fatorial *= i;
        Date after = new Date();
        int afterHours = after.getHours();
        int afterMinutes = after.getMinutes();
        int afterSeconds = after.getSeconds();
        long delta = (afterHours * 60 * 60 + afterMinutes * 60 + afterSeconds)
            - (hours * 60 * 60 + minutes * 60 + seconds);
        return delta;
    }
}















