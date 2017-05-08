package clone.swaper.test;

import clone.swaper.business.Money;
import clone.swaper.business.Term;
import clone.swaper.business.loan.Company;
import clone.swaper.business.loan.LoansQuery;
import clone.swaper.business.loan.registration.RegisterLoan;
import clone.swaper.infrastructure.command.Command;
import org.hamcrest.core.Is;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertThat;

public class TestLoan {
    static String CREATE_LOAN_URL = "/loans";
    static String GET_LOAN_URL = "/loans";
    
    public static LoansQuery.LoansBean create(TestRestTemplate restTemplate, Company company, String externalId, Money amount, Term term) {
        RegisterLoan registerLoan = new RegisterLoan(company, externalId, amount, term);
        ResponseEntity<Command.R.Void> loanCreatedResponse = restTemplate.postForEntity(CREATE_LOAN_URL, registerLoan, Command.R.Void.class);
        assertThat(loanCreatedResponse.getStatusCode(), Is.is(HttpStatus.CREATED));
        return restTemplate.getForEntity(GET_LOAN_URL, LoansQuery.LoansBean.class).getBody();
    }
    
    public static LoansQuery.LoansBean get(TestRestTemplate restTemplate) {
        return restTemplate.getForEntity(GET_LOAN_URL, LoansQuery.LoansBean.class).getBody();
    }
    
}
