package kata.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;


public class PrintToolTest {

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
