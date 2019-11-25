package exercicio4;

public class Banco {

    private Account[] accounts;

    // criar o banco com contas com o saldo a 0
    public Banco(int capacity) {
        this.accounts = new Account[capacity];
        for(int i = 0; i < capacity; i++)
            this.accounts[i] = new Account();
    }

    public int size() {
        return this.accounts.length;
    }

    public void withdraw(int account, double amount) {
        synchronized (this.accounts[account]) {
            this.accounts[account].withdraw(amount);
        }
    }

    public void deposit(int account, double amount) {
       synchronized (this.accounts[account]) {
           this.accounts[account].deposit(amount);
       }
    }

    // transferir massa
    public void transfer(int origin, int destination, double amount) {
        synchronized (this.accounts[Math.min(origin, destination)]) {
            synchronized (this.accounts[Math.max(origin, destination)]) {
                this.accounts[origin].withdraw(amount);
                this.accounts[destination].deposit(amount);
            }
        }
    }

}
