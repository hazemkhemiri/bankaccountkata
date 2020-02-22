package kata.model;

import java.util.List;

public class BankAccount {

    private String accountIdentifier;

    private List<Operation> operations;

    public Double computeAccountBalance() {
        return operations.stream().mapToDouble(Operation::amountOf).sum();
    }


}
