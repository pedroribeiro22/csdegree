public class Cliente1 implements Runnable {

    private int id;
    private Banco b;

    public Cliente1(int c, Banco b) {
        this.id = c;
        this.b = b;
    }

    public void run() {
        synchronized (this.b) {
            this.b.depositar(id, 5);
        }
    }
}
