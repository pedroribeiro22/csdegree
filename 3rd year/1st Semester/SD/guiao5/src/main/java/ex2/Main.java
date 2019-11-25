import ex2.Consumer;
import ex2.Producer;
import ex2.Warehouse;

public class Main {

    public static void main(String[] args) {

        Warehouse wh = new Warehouse();
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(new Producer(wh));
        threads[1] = new Thread(new Consumer(wh));
        threads[0].start();
        threads[1].start();
        try {
            threads[0].join();
            threads[1].join();
        }
        catch(Exception e) {
        }

    }
}

