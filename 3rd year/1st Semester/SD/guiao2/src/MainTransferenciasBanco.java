import java.util.Scanner;

public class MainTransferenciasBanco implements Runnable {

    public static Banco b;

    public void run() {
        synchronized (b) {
           b.transferir(2, 3, 10);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        b = new Banco(10);
        int n = 100000;
        Thread[] threads = new Thread[n];
        for(int i = 0; i < n; i++) {
            threads[i] = new Thread(new MainTransferenciasBanco());
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
