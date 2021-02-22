package BankImplementations;

public class BankNaive implements Bank {

    private double balance;

    public BankNaive() {
        this.balance = 0;
    }

    public double balance() {
        return this.balance;
    }

    public boolean movement(double amount) {
        double projected_balance = this.balance + amount;
        if (projected_balance < 0) return false;
        this.balance += amount;
        return true;
    }

}
