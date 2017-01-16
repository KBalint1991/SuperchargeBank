import exceptions.NotEnoughBalanceException;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Balint on 2017. 01. 16..
 */
public class BankUser implements Serializable {

    private Long accountNumber;

    private String firstName;

    private String lastName;

    private BankAccount account;

    private List<BankTransaction> transactions;

    public BankUser() {
    }

    public BankUser(BankAccount account, String firstName, String lastname) {
        long LOWER_RANGE = 0;
        long UPPER_RANGE = 1000000;
        Random random = new Random();

        this.accountNumber = LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastname;
        this.transactions = new ArrayList<>();
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public List<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public void deposit(double deposit) {
        this.account.deposit(deposit);
    }

    public void withdraw(double withdraw) throws NotEnoughBalanceException {
        this.account.withdraw(withdraw);
    }

    public void printStatement() {
        System.out.println(firstName + " " + lastName);
        this.account.printStatement();
    }

    public void addTransaction(BankTransaction transaction, BankTransaction.transactionType type) {
        transaction.setType(type);
        transactions.add(transaction);
    }

    public void addTransaction(BankTransaction transaction) {
        transactions.add(transaction);
    }

    public void printHistory() {
        System.out.println(firstName + " " + lastName + " Transaction History:");
        System.out.println("ID\tAmount\tDate\tOperation");
        for (BankTransaction transaction : transactions) {
            transaction.printTransaction();
        }
        System.out.println("Your current balance is: " + account.getBalance());
    }

    public void printTypeFilteredHistory(BankTransaction.transactionType deposit) {
        System.out.println(firstName + " " + lastName + "'s Transaction History filtered by " + deposit);
        for (BankTransaction transaction : transactions) {
            if (transaction.getType().equals(deposit)) {
                transaction.printTransaction();
            }
        }
    }

    public void printDateFilteredHistory(Timestamp from, Timestamp to) {
        System.out.println(firstName + " " + lastName + "'s Transaction History filtered by Date Range");
        for (BankTransaction transaction : transactions) {
            if (transaction.getTimestamp().after(from) && transaction.getTimestamp().before(to)) {
                transaction.printTransaction();
            }
        }
    }

    public void printTodaysHistory() {
        System.out.println(firstName + " " + lastName + "'s Transaction History filtered by Today");
        for (BankTransaction transaction : transactions) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            int difInMilis = Math.abs((int) (transaction.getTimestamp().getTime() - now.getTime()));
            int difInDays = difInMilis / (1000 * 60 * 60 * 24);
            if (difInDays <= 1) {
                transaction.printTransaction();
            }

        }
    }
}
