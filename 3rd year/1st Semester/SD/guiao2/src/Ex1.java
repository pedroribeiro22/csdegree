import java.util.Scanner;

public class Ex1 {

    public static final Counter counter = new Counter();



    private static class Counter {

       private int i;

       private Counter() {
           i = 0;
       }

       private void increment() {
           synchronized (counter) {
               i++;
           }
       }
    }



    private static class Incrementador implements Runnable {

        private final int to;

        private Incrementador(int to) {
            this.to = to;
        }

        public void run() {
           for(int i = 0; i < this.to; i++) {
               Ex1.counter.increment();
           }
        }
    }



    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(args[0]);
        int i = Integer.parseInt(args[1]);
        System.out.println("Total de incrementos esperados: " + i * n);
        Thread[] threads = new Thread[n];

        for(int j = 0; j < n; j++) {
            threads[j] = new Thread(new Incrementador(i));
            threads[j].start();
        }

        for(int j = 0; j < n; j++)
            threads[j].join();
        System.out.println(counter.i);
    }

}
