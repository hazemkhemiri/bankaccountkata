package kata.bank;

import java.util.Date;

public class Withdrawal extends Operation {

    private Withdrawal(Double amount, String description) {
        super(amount, description);
    }

    public Withdrawal(Double amount, String description, Date date) {
        super(amount, description, date);
    }

    public static Withdrawal make(Double amount, String description) {
        return new Withdrawal(amount, description);
    }

    @Override
    public Double amountOf() {
        return (-1) * getAmount();
    }

    @Override
    public String format() {
        return String.format(LINE_FORMATTER, getDescription(),
                null, getAmount(), DATE_FORMATTER.format(getOperationDate()));

    }
}
