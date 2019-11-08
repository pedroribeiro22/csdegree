package ex2;

import ex1.BoundedBuffer;

public class newConsumer implements Runnable {

    private BoundedBuffer b;
    private int tp;
    private int numOps;

    public newConsumer(BoundedBuffer b, int tp, int numOps) {
        this.b = b;
        this.tp = tp;
        this.numOps = numOps;
    }

    public void run() {

        int r;
        for(int i = 0; i < numOps; i++) {
            try {
               r = b.get();
               Thread.sleep(tp);
            }
            catch(Exception e) {
            }
        }
    }
}
