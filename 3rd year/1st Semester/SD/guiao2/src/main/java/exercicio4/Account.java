package exercicio4;

public class Account {

    private double balance;

    // criar conta
    public Account() {
        this.balance = 0;
    }

    // levantar dinheiro
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    // depositar dinheiro
    public void deposit(double amount) {
        this.balance += amount;
    }

    // ver saldo
    // para não poder ver saldos "falsos" tem de ser synchronized
    public double getBalance() {
        return this.balance;
    }
}
