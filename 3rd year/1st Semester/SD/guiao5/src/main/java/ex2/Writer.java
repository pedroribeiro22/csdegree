package ex2;

public class Writer implements Runnable {

    private RWLock rwlock;

    public Writer(RWLock rw) {
        this.rwlock = rw;
    }

    public void run() {
        try {
            Thread.sleep(100);
        }
        catch(Exception e) {

        }
        System.out.println("I'm writing, bitch! ==<===>== WRITER");
    }
}
