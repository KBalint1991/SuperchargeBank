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
        WITHDRAW,
        TRANSFERSOURCE,
        TRANSFERTARGET
    }

    private transactionType type;

    private Long transactionID;

    private Long sourceID;

    private Long targetID;

    private double amount;

    private Timestamp timestamp;

    public BankTransaction(Long transactionID, double amount, Timestamp timestamp, Long sourceID, Long targetID) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.timestamp = timestamp;
        this.sourceID = sourceID;
        this.targetID = targetID;
    }

    public BankTransaction(double amount) {
        long LOWER_RANGE = 0;
        long UPPER_RANGE = 1000000;
        Random random = new Random();

        this.transactionID = LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
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

    public Long getSourceID() {
        return sourceID;
    }

    public void setSourceID(Long sourceID) {
        this.sourceID = sourceID;
    }

    public Long getTargetID() {
        return targetID;
    }

    public void setTargetID(Long targetID) {
        this.targetID = targetID;
    }

    public void printTransaction() {
        System.out.println(transactionID + "\t" + formatAmount() + "\t" + formatDate() + "\t" + type + "\t" + formatType());
    }


    public BankTransaction clone() {
        return new BankTransaction(this.transactionID, this.amount, this.timestamp, this.sourceID, this.targetID);
    }

    public String formatDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(timestamp.getTime()));
        c.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
        return sdf.format(c.getTime());
    }

    public double formatAmount() {
        if (type.equals(transactionType.WITHDRAW) || type.equals(transactionType.TRANSFERSOURCE)) {
            return amount * -1;
        }
        return amount;
    }

    public String formatType() {
        if (type.equals(transactionType.TRANSFERSOURCE)) {
            return "Target Account: " + targetID.toString();
        }
        if (type.equals(transactionType.TRANSFERTARGET)) {
            return "Source Account: " + sourceID.toString();
        }
        return "";
    }
}
