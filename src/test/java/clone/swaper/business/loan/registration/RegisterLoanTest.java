package clone.swaper.business.loan.registration;

import clone.swaper.SwaperApplicationTest;
import clone.swaper.business.Money;
import clone.swaper.business.Term;
import clone.swaper.business.loan.Company;
import clone.swaper.business.loan.Loan;
import clone.swaper.business.loan.LoanBean;
import clone.swaper.business.loan.LoansQuery;
import clone.swaper.test.TestLoan;
import org.hamcrest.core.Is;
import org.junit.Test;

import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertThat;

public class RegisterLoanTest extends SwaperApplicationTest {
    String EXTERNAL_ID = "id";
    Money AMOUNT = Money.eur(TEN);
    Term TERM = Term.months(10);
    
    @Test
    public void registerLoan() throws Exception {
        LoansQuery.LoansBean loansBean = TestLoan.create(restTemplate, Company.SWAPER, EXTERNAL_ID, AMOUNT, TERM);
        LoanBean loanBean = loansBean.loans.stream().findFirst().get();
        assertThat(loanBean.company, Is.is(Company.SWAPER));
        assertThat(loanBean.amount, Is.is(AMOUNT));
        assertThat(loanBean.openAmount, Is.is(AMOUNT));
        assertThat(loanBean.term, Is.is(TERM));
        assertThat(loanBean.status, Is.is(Loan.Status.OPEN));
    }
    
}
