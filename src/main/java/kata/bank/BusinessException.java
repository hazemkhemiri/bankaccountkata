package kata.bank;

public class BusinessException extends Exception {

    public static final String EXCEED_OVERDRAFT = "Exceed overdraft";
    public static final String NOT_VALID = "Not a valid amount";

    public BusinessException(String message) {
        super(message);
    }

}
