package exercicio1;

import java.util.Scanner;

public class Exercicio1 implements Runnable {

    private final int writing_ceiling;

    // Define os números que cada thread vai imprimir
    public Exercicio1(int writing_ceiling) {
        this.writing_ceiling = writing_ceiling;
    }

    // Cada thread deve imprimir os números
    public void run() {
       for(int i = 1; i <= this.writing_ceiling; i++) {
           System.out.println(i);
       }
    }

    // Execução das N threads
    public static void main(String[] args) throws InterruptedException {

        System.out.println("How many threads do you want?");
        Scanner resposta = new Scanner(System.in);
        int thread_count = resposta.nextInt();
        System.out.println("What's the counting ceiling you want?");
        int counting_ceiling = resposta.nextInt();
        Thread[] threads = new Thread[thread_count];

        // Criar, efetivamente as threads
        for(int i = 0; i < thread_count; i++)
            threads[i] = new Thread(new Exercicio1(counting_ceiling));

        // Iniciar as threads
        for(int i = 0; i < thread_count; i++)
            threads[i].start();

        // Apanhar as threads
        for(int i = 0; i < thread_count; i++)
            threads[i].join();
    }
}
