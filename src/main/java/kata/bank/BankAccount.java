package kata.bank;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String accountIdentifier;

    private List<Operation> operations;

    private BankAccount(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
        operations = new ArrayList();
    }

    public static BankAccount createAccount(String iban) {
        return new BankAccount(iban);
    }

    public void makeDeposit(Double amount, String description) {
        operations.add(Deposit.make(amount, description));
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Double computeBalance() {
        return operations.stream().mapToDouble(Operation::getAmount).sum();
    }

}
