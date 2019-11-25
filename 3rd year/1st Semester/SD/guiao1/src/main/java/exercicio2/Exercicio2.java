package exercicio2;

import java.util.Scanner;

public class Exercicio2 {

    public static void main(String[] args) throws InterruptedException {

        Counter c = new Counter();
        System.out.println("How many threads do you want?");
        Scanner resposta = new Scanner(System.in);
        int threads_count = resposta.nextInt();
        System.out.println("How many times, per thread, do you want to increment the counter?");
        int increments_per_thread = resposta.nextInt();
        Thread[] threads = new Thread[threads_count];

        // Criar efetivamente as threads. Todas recebem o mesmo objeto de Counter porque o objetivo do exercício é
        // partilhamos o objeto de Counter.
        for(int i = 0; i < threads_count; i++)
            threads[i] = new Thread(new Incrementador(c, increments_per_thread));

        // Iniciar as threads
        for(int i = 0; i < threads_count; i++)
            threads[i].start();

        // Apanhar as threads
        for(int i = 0; i < threads_count; i++)
            threads[i].join();

        System.out.println(c.get());
    }
}
