package clone.swaper;

import clone.swaper.business.investor.Investor;
import clone.swaper.business.investor.InvestorId;
import clone.swaper.business.loan.Loan;
import clone.swaper.business.loan.LoanId;
import clone.swaper.business.loan.repayment.LoanRepayment;
import clone.swaper.business.loan.repayment.LoanRepaymentId;
import clone.swaper.infrastructure.persistence.Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwaperApplicationTest {
    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    private Repository<Loan, LoanId> loans;
    @Autowired
    private Repository<Investor, InvestorId> investors;
    @Autowired
    private Repository<LoanRepayment, LoanRepaymentId> loanRepayments;
    @Autowired
    private PlatformTransactionManager txManager;
    
    @Before
    @Transactional
    public void setUp() throws Exception {
        new TransactionTemplate(txManager).execute(status -> {
            loans.removeAll();
            investors.removeAll();
            loanRepayments.removeAll();
            return null;
        });
    }
    
    @Test
    public void contextLoads() {
    }
}
