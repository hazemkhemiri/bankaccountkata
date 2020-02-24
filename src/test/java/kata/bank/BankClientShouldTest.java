package kata.bank;


import static org.junit.Assert.*;

import org.junit.Test;


public class BankClientShouldTest {

    private BankAccount given_account() {
        return BankAccount.createAccount("00002555000555");
    }

    @Test
    public void be_able_to_create_account() {

        BankAccount account = given_account();

        assertNotNull(account);
        assertNotNull(account.getAccountIdentifier());
        assertEquals(0, account.getOperations().size());
    }


    @Test
    public void make_deposit_to_his_account() {

        BankAccount account = given_account();

        account.makeDeposit(20.0, "first deposit");

        assertEquals(1, account.getOperations().size());
        assertEquals(new Double(20), account.getOperations().get(0).getAmount());
    }

    public void get_balance_of_his_account() {
        BankAccount account = given_account();

        account.makeDeposit(20.0, "1st deposit");
        account.makeDeposit(10.0, "2nd deposit");

        assertEquals(2, account.getOperations().size());
        assertEquals(new Double(20), account.getOperations().get(0).getAmount());
        assertEquals(new Double(10), account.getOperations().get(1).getAmount());
        assertEquals(new Double(30), account.computeBalance());

    }



}