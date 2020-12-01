package kata.bank;


import static org.junit.Assert.*;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BankAccountTest {

    private BankAccount given_account() {
        return BankAccount.createAccount("00002555000555", 1000.0);
    }

    @Test
    public void client_should_be_able_to_create_account() {

        BankAccount account = given_account();

        assertNotNull(account);
        assertNotNull(account.getAccountIdentifier());
        assertEquals(0, account.getOperations().size());
    }


    @Test
    public void client_should_make_deposit_to_his_account() throws BusinessException {

        BankAccount account = given_account();

        account.makeDeposit(20.0, "first deposit");

        assertEquals(1, account.getOperations().size());
        assertEquals(new Double(20), account.getOperations().get(0).getAmount());
    }

    @Test
    public void should_get_balance_of_his_account() throws BusinessException {
        BankAccount account = given_account();

        account.makeDeposit(20.0, "1st deposit");
        account.makeDeposit(10.0, "2nd deposit");

        assertEquals(2, account.getOperations().size());
        assertEquals(new Double(20), account.getOperations().get(0).getAmount());
        assertEquals(new Double(10), account.getOperations().get(1).getAmount());
        assertEquals(new Double(30), account.computeBalance());
    }

    @Test(expected = BusinessException.class)
    public void should_not_set_a_null_deposit() throws BusinessException {

        BankAccount account = given_account();

        account.makeDeposit(null, "first withdrawal");
    }

    @Test(expected = BusinessException.class)
    public void should_not_set_a_zero_deposit() throws BusinessException {

        BankAccount account = given_account();

        account.makeDeposit(0.0, "first withdrawal");
    }

    @Test(expected = BusinessException.class)
    public void should_not_set_a_negative_withdrawal() throws BusinessException {

        BankAccount account = given_account();

        account.makeWithdrawal(-100.0, "first withdrawal");
    }

    @Test
    public void should_be_able_to_take_money_from_his_account() throws BusinessException {

        BankAccount account = given_account();

        account.makeWithdrawal(20.0, "first withdrawal");

        assertEquals(1, account.getOperations().size());
        assertEquals(new Double(20), account.getOperations().get(0).getAmount());
        assertEquals(new Double(-20), account.computeBalance());
    }

    @Test(expected = BusinessException.class)
    public void should_not_exceed_his_overdraft_when_take_money() throws BusinessException {

        BankAccount account = given_account();

        account.makeWithdrawal(200.0, "1st withdrawal");
        account.makeWithdrawal(900.0, "2nd withdrawal");
    }

    @Test
    public void should_can_see_all_operation_in_his_account() throws BusinessException {

        BankAccount account = given_account();

        account.makeDeposit(90.0, "1st deposit");
        account.makeDeposit(10.0, "2nd deposit");
        account.makeWithdrawal(20.0, "1st withdrawal");
        account.makeDeposit(80.0, "3rd deposit");
        account.makeWithdrawal(90.0, "2nd withdrawal");

        String displayed = account.print();
        assertTrue(displayed.contains("90.00"));
        assertTrue(displayed.contains("balance   : 70.0"));
        assertTrue(displayed.contains("2nd withdrawal\t   nu\t90.00"));
        assertTrue(displayed.contains("overdraft : 1000.0"));
    }

    //print tests

    private Date dateExample() {
        try {
            return Operation.DATE_FORMATTER.parse("2020-02-23 13:32:54");
        } catch (ParseException e) {
            return null;
        }
    }

    private Operation depositExample() {
        return new Deposit(10.2, "deposit", dateExample());
    }

    private Operation withdrawalExample() {
        return new Withdrawal(16., "withdrawal", dateExample());
    }

    @Test
    public void should_be_able_to_add_operations_with_forced_date() {
        List<Operation> list = new ArrayList<>();
        list.add(depositExample());
        list.add(withdrawalExample());
        assertEquals(2, list.size());
    }

    @Test
    public void should_show_formatted_line_for_deposit() {
        Operation operation = depositExample();
        String formattedLine = operation.format();

        System.out.println(formattedLine);

        assertEquals("             deposit\t10.20\t   nu\t2020-02-23 13:32:54\n", formattedLine);
    }

    @Test
    public void should_show_formatted_line_for_withdrawal() {
        Operation operation = withdrawalExample();
        String formattedLine = operation.format();

        System.out.println(formattedLine);

        assertEquals("          withdrawal\t   nu\t16.00\t2020-02-23 13:32:54\n", formattedLine);
    }

    @Test
    public void should_show_formatted_account_operations_as_this() {
        BankAccount account = BankAccount.createAccount("0000025500255", 100.0);
        account.getOperations().add(depositExample());
        account.getOperations().add(withdrawalExample());
        String formattedReport = account.print();
        System.out.println(formattedReport);

        assertEquals("account   : 0000025500255\n" +
                        "overdraft : 100.0\n" +
                        "             deposit\t10.20\t   nu\t2020-02-23 13:32:54\n" +
                        "          withdrawal\t   nu\t16.00\t2020-02-23 13:32:54\n" +
                        "balance   : -5.800000000000001\n",
                formattedReport);
    }

}