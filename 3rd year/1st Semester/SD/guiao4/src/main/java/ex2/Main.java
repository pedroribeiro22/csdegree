package ex2;

import ex1.BoundedBuffer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        BoundedBuffer b = new BoundedBuffer(10);
        int tc = 20;
        int tp = 100;
        int N = 10;
        int totalOps = 100;
        int C;
        int P;
        int bestC = 1;
        int bestP = 1;
        long best_time = 10000;

        for(P = 1; P <= 9; P++) {

            C = N - P;

            Thread[] produtores = new Thread[P];
            Thread[] consumidores = new Thread[C];

            int pOps = totalOps / P;
            int rest = totalOps % P;
            // criar produtores
            for(int i = 0; i < P; i++) {
                if(i < P - 1) {
                    produtores[i] = new Thread(new newProducer(b, tc, pOps));
                }
                else { produtores[i] = new Thread(new newProducer(b, tc, pOps + rest)); }
            }

            // criar consumidores
            int cOps = totalOps / C;
            int cRest = totalOps % C;
            for(int i = 0; i < C; i++) {
                if(i < C - 1) {
                    consumidores[i] = new Thread(new newConsumer(b, tp, cOps));
                }
                else { consumidores[i] = new Thread(new newConsumer(b, tc, cOps + cRest)); }
            }

            // iniciar o cronómetro
            long start_time = System.currentTimeMillis() / 1000; // em segundos

            for(int i = 0; i < P; i++) {
                produtores[i].start();
            }

            for(int i = 0; i < C; i++) {
                consumidores[i].start();
            }

            for(int i = 0; i < P; i++) {
                produtores[i].join();
            }

            for(int i = 0; i < C; i++) {
                consumidores[i].join();
            }

            long stop_time = System.currentTimeMillis() / 1000;
            long try_time = stop_time - start_time;
            if(try_time < best_time) {
                best_time = try_time;
                bestC = C;
                bestP = P;
            }

         }

        System.out.println("O melhor número de C é: " + bestC);
        System.out.println("O melhor número de P é: " + bestP);
        System.out.println("Demorando: " + best_time + " segundos.");

        // Fazer débito -> número total de operações / tempo

    }
}

