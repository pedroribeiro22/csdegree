import java.util.Scanner;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        switch(args[0]) {
            case "1": m.executeConversion();
                      break;
            case "2": m.executeMaximoNumeros();
                      break;
            case "3": m.executeDescricao();
                      break;
            case "4": m.executeMoneyConversion();
                      break;
            case "5": m.executeReadNWrite();
                      break;
            case "6": m.executeFactorial();
                      break;
            case "7": m.executeTempoGasto();
                      break;
            default : break;
        }
    }

    // Função que executa celsiusToFarenheit
    public void executeConversion() {
        Ficha1 f = new Ficha1();
        System.out.println("Introduza o valor (em Celsius) que pretende converter para Farenheit.");
        Scanner scan = new Scanner(System.in);
        double celsius = scan.nextDouble();
        double farenheit = f.celsiusToFarenheit(celsius);
        System.out.println("O valor convertido em Farenheit é: " + farenheit);
    }

    // Função que executa maximoNumeros
    public void executeMaximoNumeros() {
        Ficha1 f = new Ficha1();
        System.out.println("Introduza o primeiro inteiro que pretende comparar.");
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        System.out.println("Introduza o segundo inteiro que pretende comparar.");
        Scanner scan2 = new Scanner(System.in);
        int b = scan2.nextInt();
        int c = f.maximoNumeros(a, b);
        System.out.println("O maior dos numeros introduzidos é: " + c);
    }

    // Função que executa criaDescriçãoConta
    public void executeDescricao() {
        Ficha1 f = new Ficha1();
        System.out.println("Introduza o seu nome.");
        Scanner scan = new Scanner(System.in);
        String nome = scan.nextLine();
        System.out.println("Introduza o seu saldo.");
        Scanner scan2 = new Scanner(System.in);
        int saldo = scan2.nextInt();
        String descricao = f.criaDescricaoConta(nome, saldo);
        System.out.println(descricao);
    }

    // Funçao que executa conversao de euros para libras
    public void executeMoneyConversion() {
        Ficha1 f = new Ficha1();
        // vamos criar uma taxa de conversão ficticia
        double taxaConversao = 1.232323;
        System.out.println("Qual o valor em euros que prentede converter para libras?");
        Scanner scan = new Scanner(System.in);
        double euros = scan.nextDouble();
        double pounds = f.eurosToPounds(euros, taxaConversao);
        System.out.println("O valor convertido em libras é: " + pounds);
    }

    // Funcao que aplica readnwrite
    public void executeReadNWrite() {
        Ficha1 f = new Ficha1();
        double mean = f.readnwrite();
        System.out.println("A média dos números introduzidos é: " + mean);
    }

    // Função que executa factorial
    public void executeFactorial() {
        Ficha1 f = new Ficha1();
        System.out.println("Introduza o número cujo fatorial pretende saber.");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        long fatorial = f.factorial(num);
        System.out.println("O fatorial do número introduzido é: " + fatorial);
    }

    // Função que executa tempoGasto
    public void executeTempoGasto() {
        Ficha1 f = new Ficha1();
        System.out.println("Esta função calcula o fatorial de 5000 e de seguida diz-lhe o tempo que demorou a realizar essa mesma operação (em segundos).");
        long tempo = f.tempoGasto();
        System.out.println("Demorou " + tempo + " segundos a realizar a operação acima descrita.");
    }
}












