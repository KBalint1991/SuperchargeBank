import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Balint on 2017. 01. 16..
 */
public class BankTransaction {

    public enum transactionType {
        DEPOSIT,
        WITHDRAW
    }

    private transactionType type;

    private Long transactionID;

    private double amount;

    private Timestamp timestamp;

    public BankTransaction(Long transactionID, double amount, Timestamp timestamp) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public BankTransaction(double amount) {
        long LOWER_RANGE = 0;
        long UPPER_RANGE = 1000000;
        Random random = new Random();

        this.transactionID = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public void setType(transactionType type) {
        this.type = type;
    }

    public transactionType getType() {
        return type;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void printTransaction() {
        System.out.println(transactionID + "\t" + amount + "\t" + formatDate() + "\t" + type);
    }


    public BankTransaction clone() {
        return new BankTransaction(this.transactionID, this.amount, this.timestamp);
    }

    public String formatDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(timestamp.getTime()));
        c.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
        return sdf.format(c.getTime());
    }
}
