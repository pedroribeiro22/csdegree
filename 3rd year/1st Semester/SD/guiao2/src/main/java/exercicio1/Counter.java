package exercicio1;

public class Counter {

    private int counter;

    public Counter() {
        this.counter = 0;
    }

    public synchronized void increment() {
        this.counter++;
    }

    public int get() {
        return this.counter;
    }
}
