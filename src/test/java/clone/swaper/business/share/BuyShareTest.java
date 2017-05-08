package clone.swaper.business.share;


import clone.swaper.SwaperApplicationTest;
import clone.swaper.business.Money;
import clone.swaper.business.Term;
import clone.swaper.business.investor.InvestorsQuery;
import clone.swaper.business.loan.Company;
import clone.swaper.business.loan.LoansQuery;
import clone.swaper.test.TestInvestor;
import clone.swaper.test.TestLoan;
import clone.swaper.test.TestShare;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertThat;

public class BuyShareTest extends SwaperApplicationTest {
    
    Term TERM = Term.months(10);
    Money LOAN_AMOUNT = Money.eur(BigDecimal.valueOf(100));
    Money SHARE_AMOUNT = Money.eur(BigDecimal.valueOf(10));
    String EXTERNAL_LOAN_ID = "lId";
    String PASSWORD = "password1";
    String EMAIL = "valid@mail.com";
    
    @Test
    public void buyShare() throws Exception {
        LoansQuery.LoansBean loansBean = TestLoan.create(restTemplate, Company.SWAPER, EXTERNAL_LOAN_ID, LOAN_AMOUNT, TERM);
        InvestorsQuery.InvestorsBean investorsBean = TestInvestor.create(restTemplate, EMAIL, PASSWORD);
        UUID investorId = investorsBean.investors.stream().findFirst().get().id;
        UUID loanId = loansBean.loans.stream().findFirst().get().id;
        ShareQuery.SharesBean sharesBean = TestShare.create(restTemplate, investorId, loanId, SHARE_AMOUNT);
        ShareBean shareBean = sharesBean.shares.stream().findFirst().get();
        
        assertThat(shareBean.investorId, Is.is(investorId));
        assertThat(shareBean.loanId, Is.is(loanId));
        assertThat(shareBean.amount.value(), Is.is(SHARE_AMOUNT.value()));
    }
}
