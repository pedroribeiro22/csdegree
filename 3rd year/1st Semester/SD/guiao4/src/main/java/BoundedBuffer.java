public class BoundedBuffer {

    private final int[] buff;
    private int pos_write;

    public BoundedBuffer(int size) {
        this.buff = new int[size];
        this.pos_write = 0;
    }

    public synchronized void put(int v) throws InterruptedException {

        // bloquear enquanto está cheio
        while(this.buff.length == this.pos_write)
            this.wait();

        this.buff[this.pos_write++] = v;
        this.notifyAll();

    }

    public synchronized int get() throws InterruptedException {

        // bloquear enquanto está vazio
        while(this.pos_write == 0)
            this.wait();

        int r = this.buff[--this.pos_write];
        this.notifyAll();
        return r;

    }

    public String print_buffer() {
        String r = "{";
        for(int i = 0; i < this.pos_write; i++) {
            r += this.buff[i];
        }
        r += "}" ;

        return r;

    }

}
