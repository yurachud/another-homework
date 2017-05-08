package clone.swaper.test;

import clone.swaper.business.Money;
import clone.swaper.business.loan.Company;
import clone.swaper.business.loan.repayment.LoanRepaymentQuery;
import clone.swaper.business.loan.repayment.RegisterLoanRepayment;
import clone.swaper.infrastructure.command.Command;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TestRepayment {
    static String CREATE_LOAN_REPAYMENT_URL = "/repayments";
    static String GET_LOAN_REPAYMENT_URL = "/repayments";
    
    public static LoanRepaymentQuery.LoanRepaymentsBean create(TestRestTemplate restTemplate, Company company, String externalLoanId, String externalRepaymentId, Money amount) {
        RegisterLoanRepayment registerLoanRepayment = new RegisterLoanRepayment(company, externalLoanId, externalRepaymentId, amount);
        ResponseEntity<Command.R.Void> response = restTemplate.postForEntity(CREATE_LOAN_REPAYMENT_URL, registerLoanRepayment, Command.R.Void.class);
        Assert.assertThat(response.getStatusCode(), Is.is(HttpStatus.CREATED));
        return restTemplate.getForEntity(GET_LOAN_REPAYMENT_URL, LoanRepaymentQuery.LoanRepaymentsBean.class).getBody();
    }
    
}
