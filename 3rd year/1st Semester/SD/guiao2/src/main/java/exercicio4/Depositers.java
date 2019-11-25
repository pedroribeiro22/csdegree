package exercicio4;

public class Depositers implements Runnable {

    private Banco b;

    public Depositers(Banco b) {
        this.b = b;
    }

    public void run() {
        for(int i = 0; i < b.size(); i++)
            this.b.deposit(i, 10);
    }

}
