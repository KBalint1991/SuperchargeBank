import exceptions.NotEnoughBalanceException;
import exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Balint on 2017. 01. 16..
 */
public class Bank {

    private List<BankUser> users;

    public Bank() {
        this.users = new ArrayList<>();
    }

    public Bank(List<BankUser> users) {
        this.users = users;
    }

    public void addUser(BankUser user) {
        this.users.add(user);
    }

    public BankUser getUser(Long accountNumber) throws UserNotFoundException {
        BankUser searchedUser = null;
        for (BankUser user : users) {
            if (Objects.equals(user.getAccountNumber(), accountNumber)) {
                searchedUser = user;
            }
        }
        if (searchedUser == null) {
            throw new UserNotFoundException("User not found with this account number!");
        }
        return searchedUser;
    }

    public BankTransaction transferTo(Long sourceAccount, Long targetAccount, double amount) throws UserNotFoundException, NotEnoughBalanceException {
        BankTransaction transaction = new BankTransaction(amount);
        BankUser source = getUser(sourceAccount);
        source.withdraw(amount);
        BankUser target = getUser(targetAccount);
        target.deposit(amount);
        return transaction;
    }
}
