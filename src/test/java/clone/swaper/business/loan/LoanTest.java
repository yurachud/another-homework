package clone.swaper.business.loan;

import clone.swaper.business.Money;
import clone.swaper.business.Term;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class LoanTest {
    
    @Test
    public void shouldReduceOpenAmountOnRepayment() throws Exception {
        Loan loan = new Loan(Company.SWAPER, "id", Money.eur(100), Term.months(10));
        Money repaymentAmount = Money.eur(22.5);
        
        loan.repay(repaymentAmount);
        
        assertThat(loan.openAmount(), Is.is(Money.eur(77.5)));
    }
}