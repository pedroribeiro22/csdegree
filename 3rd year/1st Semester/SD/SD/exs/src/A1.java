public class A1 implements Runnable {

    /**
     * When an object implementing interface 'Runnable' is used to create a thread, starting the thread causes the
     * object's run method to be called in separately executing thread.
     */
    public void run() {

        for(int j = 1; j <= 5; j++) {
            System.out.println(j);
        }

    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        Thread[] threads = new Thread[n];

        for(int j = 0; j < n; j++) {
            threads[j] = new Thread(new A1());
            threads[j].start();
        }

        for(int j = 0; j < n; j++) {
            try {
              threads[j].join();
            }
            catch(Exception e) {
            }
        }

    }
}
