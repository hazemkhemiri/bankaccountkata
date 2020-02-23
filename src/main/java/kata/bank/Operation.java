package kata.bank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Operation {
    private String description;
    private Double amount;
    private Date operationDate;

    protected static String LINE_FORMATTER = "%20s\t%5.2f\t%5.2f\t%s\n";
    protected static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected Operation(Double amount, String description, Date date) {
        this.amount = amount;
        this.operationDate = date;
        this.description = description;
    }

    protected Operation(Double amount, String description) {
        this(amount, description, new Date());
    }

    public Double getAmount() {
        return amount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public String getDescription() {
        return description;
    }

    public abstract Double amountOf();

    public abstract String format();
}
