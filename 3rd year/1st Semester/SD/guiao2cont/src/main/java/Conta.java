public class Conta {

    private float balance;

    // Cria uma conta e inicializa o seu saldo a 0
    public Conta() {

        this.balance = 0;

    }

    // Permite levantar dinheiro de uma conta
    public synchronized void levantar(float amount) {

        this.balance -= amount;

    }

    // Permite depositar dinheiro numa conta
    public synchronized void depositar(float amount) {

        this.balance += amount;

    }

    // Permite consultar o saldo de uma conta
    public synchronized float consultar() {

        return this.balance;

    }
}
