package exercicio2;

public class Withdrawlers implements Runnable {

    private Banco b;

    // construtor
    public Withdrawlers(Banco b) {
        this.b = b;
    }

    // run
    public void run() {
        for(int i = 0; i < this.b.size(); i++)
            synchronized (b) {
                this.b.withdraw(i, 10);
            }
    }
}
