import exceptions.NotEnoughBalanceException;
import exceptions.UserNotFoundException;

/**
 * Created by Balint on 2017. 01. 16..
 */
public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();

        BankAccount firstAccount = new BankAccount(0.);
        BankUser firstUser = new BankUser(firstAccount, "Kovacs", "Balint");

        BankAccount secondAccount = new BankAccount(0.);
        BankUser secondUser = new BankUser(secondAccount, "Lassu", "Lilla");

        bank.addUser(firstUser);
        bank.addUser(secondUser);

        try {
            BankTransaction depositTran = bank.deposit(firstUser.getAccountNumber(), 1000.);
            firstUser.addTransaction(depositTran);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        firstUser.printStatement();
        System.out.println("------------------------------------------------");

        try {
            BankTransaction withdrawTran = bank.withdraw(firstUser.getAccountNumber(), 200.);
            firstUser.addTransaction(withdrawTran);
        } catch (UserNotFoundException | NotEnoughBalanceException e) {
            e.printStackTrace();
        }
        firstUser.printHistory();
        System.out.println("------------------------------------------------");
        firstUser.printStatement();
        System.out.println("------------------------------------------------");

        try {
            BankTransaction transaction = bank.transferTo(firstUser.getAccountNumber(), secondUser.getAccountNumber(), 500.);
            firstUser.addTransaction(transaction, BankTransaction.transactionType.TRANSFERSOURCE);
            secondUser.addTransaction(transaction.clone(), BankTransaction.transactionType.TRANSFERTARGET);
            BankTransaction secondTransaction = bank.transferTo(secondUser.getAccountNumber(), firstUser.getAccountNumber(), 250.);
            secondUser.addTransaction(secondTransaction, BankTransaction.transactionType.TRANSFERSOURCE);
            firstUser.addTransaction(secondTransaction.clone(), BankTransaction.transactionType.TRANSFERTARGET);
        } catch (UserNotFoundException | NotEnoughBalanceException e) {
            e.printStackTrace();
        }
        firstUser.printHistory();
        System.out.println("------------------------------------------------");
        secondUser.printHistory();
        System.out.println("------------------------------------------------");
        firstUser.printStatement();
        System.out.println("------------------------------------------------");
        secondUser.printStatement();
        System.out.println("------------------------------------------------");
        firstUser.printTypeFilteredHistory(BankTransaction.transactionType.DEPOSIT);
        System.out.println("------------------------------------------------");
        firstUser.printTypeFilteredHistory(BankTransaction.transactionType.WITHDRAW);
        System.out.println("------------------------------------------------");
        firstUser.printTodaysHistory();
    }
}
