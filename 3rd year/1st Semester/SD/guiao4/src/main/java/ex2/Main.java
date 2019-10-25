package ex2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static final int N = 10;

    // gerador de números aleatórios
    static int generate_random_number(int min, int max) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * max + min;
        int randomInt = (int) randomDouble;
        return randomInt;
    }

    private static class Atomic_Integer {

        private int counter = 0;
        private Lock l = new ReentrantLock();

        public void increment() {
           l.lock();
           this.counter++;
           l.unlock();
        }

        public int get() {
           return this.counter;
        }

    }

    private static class Produtor implements Runnable {

        private BoundedBuffer pbf;
        private Atomic_Integer inteiros_produzidos;

        public void run() {
            int r;
            for(int i = 0; i < 20; i++) {
                r = generate_random_number(1, 20);
                try {
                    pbf.put(r);
                    inteiros_produzidos.increment();
                    System.out.println(pbf.print_buffer());
                }
                catch(Exception e) {
                }
            }
        }

        public Produtor(BoundedBuffer pbf, Atomic_Integer inteiros_produzidos) {
            this.pbf = pbf;
            this.inteiros_produzidos = inteiros_produzidos;
        }

    }

    private static class Consumidor implements Runnable {

        private BoundedBuffer cbf;
        private Atomic_Integer inteiros_consumidos;

        public void run() {
            int r;
            for(int i = 0; i < 20; i++) {
                try {
                    r = cbf.get();
                    inteiros_consumidos.increment();
                    System.out.println("Got: " + r);
                }
                catch(Exception e) {
                }
            }

        }

        public Consumidor(BoundedBuffer cbf, Atomic_Integer inteiros_consumidos) {
            this.cbf = cbf;
            this.inteiros_consumidos = inteiros_consumidos;
        }

    }

    // cria as threads
    public static Thread[] create_threads(int produtores, int consumidores, BoundedBuffer bf, Atomic_Integer inteiros_produzidos, Atomic_Integer inteiros_consumidos) {
        int i = 0;
        Thread[] r = new Thread[produtores + consumidores];
        for(; i < produtores; i++) r[i] = new Thread(new Produtor(bf, inteiros_produzidos));
        for(; i - produtores < consumidores; i++) r[i] = new Thread(new Consumidor(bf, inteiros_consumidos));
        return r;
    }

    static long run_threads(Thread[] threads, int n) {

        long start_time = System.nanoTime();

        for(int i = 0; i < n; i++) {
           threads[i].start();
        }

        for(int i = 0; i < n; i++) {
            try {
               threads[i].join();
            }
            catch(Exception e) {
            }
        }

        return System.nanoTime() - start_time;
    }

    public static void main(String[] args) {

        BoundedBuffer bf = new BoundedBuffer(N);
        Atomic_Integer inteiros_produzidos = new Atomic_Integer();
        Atomic_Integer inteiros_consumidos = new Atomic_Integer();

        int bestP = 1, bestC = 1;
        long best_overall_time = 100000000;
        long time_took;

        for(int i = 0; i < N; i++) {
            Thread[] threads = create_threads(i, N - i, bf, inteiros_produzidos, inteiros_consumidos);
            time_took = run_threads(threads, N);
            if(i == 0) best_overall_time = time_took;
            if(time_took < best_overall_time) {
                best_overall_time = time_took;
                bestP = i;
                bestC = N - i;
            }
        }

        System.out.println("P: " + bestP);
        System.out.println("C: " + bestC);

        System.out.println("Foram produzidos " + inteiros_produzidos.get() + " inteiros.");
        System.out.println("Foram consumidos " + inteiros_consumidos.get() + " inteiros.");

    }
}

