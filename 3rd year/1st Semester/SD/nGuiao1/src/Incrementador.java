public class Incrementador implements Runnable {

    private Counter c;
    private int i;

    public Incrementador(Counter c, int i) {
        this.c = c;
        this.i = i;
    }

    public void run() {
        for(int k = 0; k < this.i; k++)
            this.c.increment();
    }

}
