package exercicio1;

import java.util.Scanner;

public class Exercicio1 {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        System.out.println("How many threads do you want?");
        Scanner resposta = new Scanner(System.in);
        int threads_count = resposta.nextInt();
        System.out.println("How many times, per thread, do you want to increment the counter?");
        int increments_per_threads = resposta.nextInt();
        Thread[] threads = new Thread[threads_count];

        // criar efetivamente as threads
        for(int i = 0; i < threads_count; i++)
            threads[i] = new Thread(new Incrementador(c, increments_per_threads));

        // iniciar as threads
        for(int i = 0; i < threads_count; i++)
            threads[i].start();

        // esperar pelas threads
        for(int i = 0; i < threads_count; i++)
            threads[i].join();

        System.out.println(c.get());
    }
}
