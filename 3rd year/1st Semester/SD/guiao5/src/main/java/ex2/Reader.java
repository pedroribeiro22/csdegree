package ex2;

public class Reader implements Runnable {

    private RWLock rwlock;

    public Reader(RWLock rw) {
       this.rwlock = rwlock;
    }

    public void run() {
        try {
           Thread.sleep(20);
        }
        catch(Exception e) {

        }
        System.out.println("I'm reading, bitch!");
    }

}
