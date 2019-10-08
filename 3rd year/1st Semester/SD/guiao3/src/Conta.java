public class Conta {

    private float balance;

    public Conta() {
        this.balance = 0;
    }

    public synchronized void depositar(double amount) {
        this.balance += amount;
    }

    public synchronized void levantar(double amount) {
        this.balance -= amount;
    }

    public synchronized float consultar() {
        return this.balance;
    }
}
