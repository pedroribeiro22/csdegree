package tf.bank;

public interface Bank {
   
    public int balance(int account_id);

    public boolean movement(int account_id, int amount);

}
