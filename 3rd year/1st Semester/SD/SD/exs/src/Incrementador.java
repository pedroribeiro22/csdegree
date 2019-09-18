public class Incrementador implements Runnable {

    private Counter c;

    public Incrementador(Counter c) {
        this.c = c;
    }

    public void run() {

        int i = 5;

        for(int j = 0; j < 5; j++) {
            this.c.increment();
        }

    }
}
