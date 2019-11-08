package ex2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWLock {

    private ReentrantLock lock;
    private Condition readWait;
    private Condition writeWait;
    private int readers;
    private int writers;

    public RWLock() {
        this.lock = new ReentrantLock();
        this.readWait = this.lock.newCondition();
        this.writeWait = this.lock.newCondition();
        this.readers = 0;
        this.writers = 0;
    }

    public void readLock() throws InterruptedException{
       this.lock.lock();

       while(writers > 0) {
           this.readWait.await();
       }
       readers++;

       this.lock.unlock();
    }

    public void readUnlock() {
       this.lock.lock();

       readers--;

       if(readers == 0) {
           this.readWait.signal();
       }

       this.lock.unlock();
    }

    public void writeLock() throws InterruptedException {
       this.lock.lock();

       while(readers > 0 || writers > 0) {
            this.writeWait.await();
       }

       writers = 1;

       this.lock.unlock();
    }

    public void writeUnlock() {
       this.lock.lock();

       writers = 0;

       writeWait.signal();
       readWait.signalAll();

       this.lock.unlock();
    }
}
