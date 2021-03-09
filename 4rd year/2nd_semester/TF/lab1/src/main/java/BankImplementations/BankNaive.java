package BankImplementations;

public class BankNaive implements Bank {

    private int[] balance = { 10000 };

    public int balance(int account_id) {
        return this.balance[account_id];
    }

    public boolean movement(int account_id, int amount) {
        int projected_balance = this.balance[account_id] + amount;
        if (projected_balance < 0) return false;
        this.balance[account_id] += amount;
        return true;
    }

}
