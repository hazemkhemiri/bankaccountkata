package kata.bank;

public class Deposit extends Operation {

    private Deposit(Double amount, String description) {
        super(amount, description);
    }

    public static Deposit make(Double amount, String description) {
        return new Deposit(amount, description);
    }

    public Double amountOf() {
        return getAmount();
    }

}
