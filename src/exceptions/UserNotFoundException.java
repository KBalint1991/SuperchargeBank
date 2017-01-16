package exceptions;

/**
 * Created by Balint on 2017. 01. 16..
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
