public class Counter {

    int i;

    /**
     * Constructor
     */
    public Counter() {
        i = 0;
    }

    /**
     * Increments the value of the counter variable.
     */
    public void increment() {
        i++;
    }

    /**
     * Allows to get the current value of the counter.
     * @return Current value of the counter.
     */
    public int get() {
        return this.i;
    }
}
