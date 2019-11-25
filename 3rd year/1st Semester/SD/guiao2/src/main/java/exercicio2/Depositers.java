package exercicio2;

public class Depositers implements Runnable {

    private Banco b;

    //
    public Depositers(Banco b) {
        this.b = b;
    }

    public void run() {
        // vamos depositar 5 euros a toda a gente
        for(int i = 0; i < this.b.size(); i++)
            synchronized (b) {
                b.deposit(i, 5);
            }
    }
}
