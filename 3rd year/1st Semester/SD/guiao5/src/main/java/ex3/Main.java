package ex3;

public class Main {

    public static void main(String[] args) {
        RWLock rw = new RWLock();
        Thread[] writers = new Thread[15];
        Thread[] readers = new Thread[15];
        for(int i = 0; i < 15; i++) {
            writers[i] = new Thread(new Writer(rw));
            readers[i] = new Thread(new Reader(rw));
        }
        for(int i = 0; i < 15; i++) {
           writers[i].start();
           readers[i].start();
        }

        for(int i = 0; i < 15; i++) {
            try {
               writers[i].join();
               readers[i].join();
            }
            catch(Exception e) {
            }
        }
    }
}
