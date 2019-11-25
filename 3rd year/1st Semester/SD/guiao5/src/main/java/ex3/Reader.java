package ex3;

public class Reader implements Runnable {

    private RWLock rwlock;

    public Reader(RWLock rw) {
       this.rwlock = rw;
    }

    public void run() {
        try {
           rwlock.readLock();
           System.out.println("---READER---");
           Thread.sleep(20);
        }
        catch(Exception e) {

        }
        rwlock.readUnlock();
    }

}
