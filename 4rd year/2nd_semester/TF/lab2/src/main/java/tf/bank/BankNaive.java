package tf.bank;

public class BankNaive implements Bank {
    
    private int[] accounts;

    public BankNaive(int account_count) {
        this.accounts = new int[account_count];
        for(int i = 0; i < account_count; i++)
            this.accounts[i] = 10000;
    }

    public int balance(int account_id) {
        return this.accounts[account_id];
    }

    public boolean movement(int account_id, int amount) {
        if(this.accounts[account_id] + amount < 0) {
            return false;
        } else {
            this.accounts[account_id] += amount;
            return true;
        }
    }
}
