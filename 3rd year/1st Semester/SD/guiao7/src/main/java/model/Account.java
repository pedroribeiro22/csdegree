package model;

import exceptions.UnsuficienteFunds;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private int account_id;
    private double balance;
    private ReentrantLock lock;

    public Account(final int account_id, final double initial_balance) {
        this.account_id = account_id;
        this.balance = initial_balance;
        this.lock = new ReentrantLock();
    }

    public void withdraw(double amount) throws UnsuficienteFunds {
        if(amount > this.balance) throw new UnsuficienteFunds(0);
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double get_balance() {
        return this.balance;
    }

    public int get_account_id() {
        return this.account_id;
    }
}
