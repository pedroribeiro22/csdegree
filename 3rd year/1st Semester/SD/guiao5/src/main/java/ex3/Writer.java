package ex3;

public class Writer implements Runnable {

    private RWLock rwlock;

    public Writer(RWLock rw) {
        this.rwlock = rw;
    }

    public void run() {
        try {
            rwlock.writeLock();
            System.out.println("<==WRITER==>");
            Thread.sleep(100);
        }
        catch(Exception e) {

        }
        rwlock.writeUnlock();
    }
}
