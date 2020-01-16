package model;

import exceptions.InexistentAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private int next_account_id;
    private Map<Integer, Account> accounts;
    private ReentrantLock lock;

    public Bank() {
        this.next_account_id = 0;
        this.accounts = new HashMap<Integer, Account>();
        this.lock = new ReentrantLock();
    }

    public int create_account(final double initial_balance) {
        this.lock.lock();
        Account a = new Account(next_account_id++, initial_balance);
        this.accounts.put(a.get_account_id(), a);
        this.lock.unlock();
        return a.get_account_id();
    }

    public double close_account(final int account_id) throws InexistentAccount {
        double r = -1;
        this.lock.lock();
        if(this.accounts.containsKey(account_id)) {
            r = this.accounts.get(account_id).get_balance();
            this.accounts.remove(account_id);
            this.lock.unlock();
        } else {
            this.lock.unlock();
            throw new InexistentAccount();
        }
        return r;
    }

    public void transfer(int from, int to, double amount) {
        int min = Math.min(from, to);
        int max = Math.max(from, to);

        this.lock.lock();
    }

}
