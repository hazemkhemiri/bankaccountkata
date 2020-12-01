package kata.bank;

import java.util.Date;

public class Deposit extends Operation {

    private Deposit(Double amount, String description) {
        super(amount, description);
    }

    public Deposit(Double amount, String description, Date date) {
        super(amount, description, date);
    }

    public static Deposit make(Double amount, String description) {
        return new Deposit(amount, description);
    }

    @Override
    public Double amountOf() {
        return getAmount();
    }

    @Override
    public String format() {
        return String.format(LINE_FORMATTER, getDescription(),
                getAmount(), null, DATE_FORMATTER.format(getOperationDate()));

    }

}
