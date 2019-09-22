public class Exercicio2 {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);  /* Número de threads */
        int i = Integer.parseInt(args[1]);  /* Incremento */
        Counter c = new Counter();
        Thread[] threads = new Thread[n];
        for(int k = 0; k < n; k++) {
            threads[k] = new Thread(new Incrementador(c, i));
            threads[k].start();
        }
        for(int k = 0; k < n; k++) {
            try {
               threads[k].join();
            }
            catch(Exception e) {
                System.out.println("Something went terribly wrong!");
            }
        }
    }
}
