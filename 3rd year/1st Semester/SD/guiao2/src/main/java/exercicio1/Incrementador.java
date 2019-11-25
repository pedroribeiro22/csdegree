package exercicio1;

public class Incrementador implements Runnable {

    private Counter counter;
    private int increments;

    // Não vou usar este construtor
    public Incrementador(int increments) {
        this.counter = new Counter();
        this.increments = increments;
    }

    // Vou usar este construtor
    public Incrementador(Counter counter, int increments) {
        this.counter = counter;
        this.increments = increments;
    }

    public void run() {
        for(int i = 0; i < increments; i++)
            this.counter.increment();
    }
}
