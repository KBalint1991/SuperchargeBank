package exceptions;

/**
 * Created by Balint on 2017. 01. 16..
 */
public class NotEnoughBalanceException extends Exception {
    public NotEnoughBalanceException() {
        super();
    }

    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
