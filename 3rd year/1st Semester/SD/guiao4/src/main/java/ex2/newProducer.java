package ex2;

import ex1.BoundedBuffer;

public class newProducer implements Runnable {

    private BoundedBuffer b;
    private int tp;
    private int numOps;

    public newProducer(BoundedBuffer b, int tp, int numOps) {
        this.b = b;
        this.tp = tp;
        this.numOps = numOps;
    }

    public void run() {

        for(int i = 0; i < this.numOps; i++) {
            try {
                b.put(1);
                Thread.sleep(tp);
            }
            catch(Exception e) {
            }
        }
    }
}
