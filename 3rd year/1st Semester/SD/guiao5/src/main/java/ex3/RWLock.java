package ex3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWLock {

    private ReentrantLock lock;
    private Condition readWait;
    private Condition writeWait;
    private int readers;
    private int writers;
    // para eliminar `starvation`
    private int write_requests;
    private int read_requests;
    private int max_priority = 3;
    private int readers_priority;
    private int writers_priority;

    public RWLock() {
        this.lock = new ReentrantLock();
        this.readWait = this.lock.newCondition();
        this.writeWait = this.lock.newCondition();
        this.readers = 0;
        this.writers = 0;
        this.write_requests = 0;
        this.read_requests = 0;
        this.readers_priority = 0;
        this.writers_priority = 0;
    }

    public void readLock() throws InterruptedException{
       this.lock.lock();

       read_requests++;

       while(writers == 1 || (write_requests > 0 && readers_priority >= max_priority)) {
           this.readWait.await();
       }

       read_requests--;

       writers_priority = 0;
       readers_priority++;
       readers++;

       this.lock.unlock();
    }

    public void readUnlock() {
       this.lock.lock();

       readers--;

       if(readers == 0) {
           this.writeWait.signal();
       }


       this.lock.unlock();
    }

    public void writeLock() throws InterruptedException {
       this.lock.lock();

       write_requests++;

       while(readers > 0 || writers > 0 || (read_requests > 0 && writers_priority >= max_priority)) {
            this.writeWait.await();
       }

       write_requests--;

       writers = 1;

       readers_priority = 0;
       writers_priority++;

       this.lock.unlock();
    }

    public void writeUnlock() {
       this.lock.lock();

       writers = 0;

       // writeWait.signal();
       readWait.signalAll();
       writeWait.signal();

        //writeWait.signal();

       // se os anteriores estiverem trocados lêm primeiro

       this.lock.unlock();
    }
}
