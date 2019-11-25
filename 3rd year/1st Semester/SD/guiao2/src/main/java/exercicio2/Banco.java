package exercicio2;

public class Banco {

    // O banco é definido como um array de contas
    private int[] accounts;
    private int bank_size;

    // Criar banco com `n` contas
    public Banco(int n) {
        this.accounts = new int[n];
        this.bank_size = n;
    }

    // tamanho do banco
    public int size() {
        return this.bank_size;
    }

    // levantar dinheiro
    public void withdraw(int account, double amount) {
        this.accounts[account] -= amount;
    }

    // depositar dinheiro
    public void deposit(int account, double amount) {
        this.accounts[account] += amount;
    }

    public void transfer(int origin, int destination, double amount) {
        this.accounts[origin] -= amount;
        this.accounts[destination] += amount;
    }

    // consultar saldo
    public double account_balance(int account) {
        return this.accounts[account];
    }
}
