package exercicio4;

public class Withdrawlers implements Runnable {

    private Banco b;

    public Withdrawlers(Banco b) {
        this.b = b;
    }

    public void run() {
        for(int i = 0; i < b.size(); i++)
            this.b.withdraw(i, 5);
    }
}
