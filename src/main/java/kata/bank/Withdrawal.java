package kata.bank;

public class Withdrawal extends Operation {

    private Withdrawal(Double amount, String description) {
        super(amount, description);
    }

    public static Withdrawal make(Double amount, String description) {
        return new Withdrawal(amount, description);
    }

    @Override
    public Double amountOf() {
        return (-1) * getAmount();
    }
}
