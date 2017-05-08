package clone.swaper.business.loan.repayment;

import clone.swaper.SwaperApplicationTest;
import clone.swaper.business.Money;
import clone.swaper.business.Term;
import clone.swaper.business.loan.Company;
import clone.swaper.business.loan.Loan;
import clone.swaper.business.loan.LoanBean;
import clone.swaper.business.loan.LoansQuery;
import clone.swaper.test.TestLoan;
import clone.swaper.test.TestRepayment;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertThat;

public class LoanRepaymentTest extends SwaperApplicationTest {
    String EXTERNAL_REPAYMENT_ID = "rId";
    Term TERM = Term.months(10);
    String EXTERNAL_LOAN_ID = "lId";
    Money LOAN_AMOUNT = Money.eur(BigDecimal.valueOf(100));
    Money REPAYMENT_AMOUNT = Money.eur(TEN);
    
    @Test
    public void registerLoanRepayment() throws Exception {
        TestLoan.create(restTemplate, Company.SWAPER, EXTERNAL_LOAN_ID, LOAN_AMOUNT, TERM);
        LoanRepaymentQuery.LoanRepaymentsBean repaymentsBean = TestRepayment.create(restTemplate, Company.SWAPER, EXTERNAL_LOAN_ID, EXTERNAL_REPAYMENT_ID, REPAYMENT_AMOUNT);
        LoanRepaymentBean repaymentBean = repaymentsBean.repayments.stream().findFirst().get();
        
        assertThat(repaymentBean.amount, Is.is(REPAYMENT_AMOUNT));
        
        LoansQuery.LoansBean loansBean = TestLoan.get(restTemplate);
        LoanBean loanBean = loansBean.loans.stream().findFirst().get();
        
        assertThat(loanBean.status, Is.is(Loan.Status.OPEN));
        assertThat(loanBean.openAmount.value(), Is.is(LOAN_AMOUNT.value().subtract(REPAYMENT_AMOUNT.value())));
    }
    
}
