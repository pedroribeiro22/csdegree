public class Conta {

    // Float que guarda o saldo da conta
    private float balance;

    // Construtor -> cria uma conta, iniciando-a com o saldo a 0
    public Conta() {

        this.balance = 0;

    }

    // Depósito numa conta
    public synchronized void depositar(double amount) {

        this.balance += amount;

    }

    // Levantamento de uma conta
    public synchronized void levantar(double amount) {

        this.balance -= amount;

    }

    // Consulta do saldo de uma conta
    public synchronized float consultar() {

        return this.balance;

    }
}
