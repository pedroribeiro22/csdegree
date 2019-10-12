public class Cliente2 implements Runnable {

    private int id;
    private Banco b;

    public Cliente2(int c, Banco b) {
        this.id = c;
        this.b = b;
    }

    public void run() {
        synchronized (this.b) {
            this.b.levantar(id, 5);
        }
    }
}
