package kata.bank;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String accountIdentifier;
    private Double overDraft;
    private List<Operation> operations;

    private BankAccount(String accountIdentifier, Double overDraft) {
        this.accountIdentifier = accountIdentifier;
        this.overDraft = overDraft;
        operations = new ArrayList<>();
    }

    public static BankAccount createAccount(String iban, Double overdraft) {
        return new BankAccount(iban, overdraft);
    }

    public void makeDeposit(Double amount, String description) throws BusinessException {
        checkAmount(amount);
        operations.add(Deposit.make(amount, description));
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Double computeBalance() {
        return operations.stream().mapToDouble(Operation::amountOf).sum();
    }

    public void makeWithdrawal(Double amount, String description) throws BusinessException {
        checkAmount(amount);
        checkOverdraft(amount);
        operations.add(Withdrawal.make(amount, description));
    }

    private void checkAmount(Double amount) throws BusinessException {
        if (amount == null || amount <= 0) {
            throw new BusinessException(BusinessException.NOT_VALID);
        }
    }

    private void checkOverdraft(Double amount) throws BusinessException {
        if (computeBalance() -  amount < -1 * overDraft) {
            throw new BusinessException(BusinessException.EXCEED_OVERDRAFT);
        }
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append("account   : ").append(accountIdentifier).append('\n');
        builder.append("overdraft : ").append(overDraft).append('\n');
        operations.forEach( x -> builder.append(x.format()) );
        builder.append("balance   : ").append(computeBalance()).append('\n');
        return builder.toString();
    }
}
