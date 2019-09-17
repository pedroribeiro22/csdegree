public class A2 {

    public static void main(String[] args) {
        Counter c = new Counter();
        int n = 10;
        Thread[] threads = new Thread[n];
        for(int i = 0; i < n; i++) {
            threads[i] = new Thread(new Incrementador(c));
            threads[i].start();
        }

        for(int i = 0; i < n; i++) {
            try {
                threads[i].join();
            }
            catch(Exception e) {
            }
        }

    }
}
