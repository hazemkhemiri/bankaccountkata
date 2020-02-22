package kata.model;

public class Deposit extends Operation {

    private Deposit(Double amount, String description) {
        super(amount, description);
    }

    public Deposit make(Double amount, String description) {
        return new Deposit(amount, description);
    }

    public Double amountOf() {
        return getAmount();
    }

    @Override
    public String format() {
        return String.format(FORMATTER, getDescription(), null, getAmount(), getOperationDate());
    }

}
