import java.util.concurrent.locks.ReentrantLock;

public class Account {

   private double balance;
   public final ReentrantLock lock;

   public Account(double initialBalance) {

      this.balance = initialBalance;
      this.lock = new ReentrantLock();

   }

   public void withdraw(double amount) throws NotEnoughFounds {

      if(amount > this.balance) throw new NotEnoughFounds(amount);
      this.balance -= amount;

   }

   public void deposit(double amount) {

      this.balance += amount;

  }

   public double getBalance() {

      return this.balance;

  }

}
