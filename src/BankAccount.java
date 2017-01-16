import exceptions.NotEnoughBalanceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balint on 2017. 01. 16..
 */
public class BankAccount {

    private double balance;

    private List<BankTransaction> transactions;

    public BankAccount(double balance) {
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public List<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactions(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public void deposit(double deposit) {
        this.balance += deposit;
    }

    public void withdraw(double withdraw) throws NotEnoughBalanceException {
        this.balance -= withdraw;
        if (this.balance < 0) {
            this.balance += withdraw;
            throw new NotEnoughBalanceException("You don't have that much money! Your balance is: " + this.balance);
        }
    }

    public void printStatement() {
        System.out.println("Your current balance is: " + this.balance);
    }
}
