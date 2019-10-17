import java.util.concurrent.locks.ReentrantLock;

public class Account {

   private double balance;
   public final ReentrantLock lock;

   // Criar uma conta
   public Account(double initialBalance) {

      this.balance = initialBalance;
      this.lock = new ReentrantLock();

   }

   // Levantar dinheiro
   public void withdraw(double amount) throws NotEnoughFounds {

      if(amount > this.balance) throw new NotEnoughFounds(amount);
      this.balance -= amount;

   }

   // Depositar dinheiro
  public void deposit(double amount) {

      this.balance += amount;

  }

  public double getBalance() {

      return this.balance;

  }

}
