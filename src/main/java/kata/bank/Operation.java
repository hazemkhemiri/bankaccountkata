package kata.bank;

import java.util.Date;

public abstract class Operation {
    private String description;
    private Double amount;
    private Date operationDate;

    private Operation(Double amount, String description, Date date) {
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
}
