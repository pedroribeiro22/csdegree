import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private Map<Integer, Account> accounts;
    private ReentrantLock lock;


    // Criar um objeto do tipo `Banco`
    public Bank() {

        this.accounts = new HashMap<>();
        this.lock = new ReentrantLock();

    }

    // Criar uma `Account` no `Banco`
    public int createAccount(double initialBalance) {

        this.lock.lock();
        int id = this.accounts.keySet().stream().max(Integer :: compareTo).orElse(-1) + 1;
        this.accounts.put(id, new Account(initialBalance));
        this.lock.unlock();
        return id;

    }

    // Fechar uma `Account` no `Banco`
    public double closeAccount(int id) throws InvalidAccount {

        this.lock.lock();
        if(this.accounts.containsKey(id)) {

            this.accounts.get(id).lock.lock();
            Account a = this.accounts.remove(id);
            a.lock.unlock();
            this.lock.unlock();
            return a.getBalance();

        } else {

            this.lock.unlock();
            throw new InvalidAccount(id);

        }

        // O que aqui está é um bocado equivalente a ter um `finally` (porque o lock.unlock() é executado de qualquer
        // forma.

    }

    public void transfer(int from, int to, double amount) throws InvalidAccount, NotEnoughFounds {

        // Fazer a marosca que aprendemos na última aula para evitar deadlocks (lockar por ordem)
        int min = Math.min(from ,to);
        int max = Math.max(from, to);

        this.lock.lock();

        // Os objetos `Account` servem para que o banco fique locked menos tempo

        Account a1 = this.accounts.get(min);
        Account a2 = this.accounts.get(max);

        this.lock.unlock();

        if(a1 == null) throw new InvalidAccount(min);
        if(a2 == null) throw new InvalidAccount(max);

        a1.lock.lock();
        a2.lock.lock();

        try {
            a1.withdraw(amount);
            a2.deposit(amount);
        }

        finally {
            a2.lock.unlock();
            a1.lock.unlock();
        }

    }

    public double[] totalBalance(int[] contas) {

       double[] ret = new double[contas.length];

        // Podemos tirar com uma passagem as contas que nos interessam para depois tirarmos os locks do banco para
        // podermos operar sobre as contas.

        List<Account> c = new ArrayList<>();

        this.lock.lock();

        // Fazer o que disse acima
        for(Integer key : contas) {
            Account a = this.accounts.get(key);
            if(a == null) continue; // safoda
            a.lock.lock();
            c.add(a);
        }

        this.lock.unlock();

        for(int i = 0; i < c.size(); i++) {
            Account a = c.get(i);
            ret[i] = a.getBalance();
            a.lock.unlock();
        }

        return ret;

    }

    // Retorna o saldo de um cliente
    public double getClientBalance(int client) {

        return this.accounts.get(client).getBalance();

    }
}
