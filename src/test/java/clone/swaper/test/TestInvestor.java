package clone.swaper.test;

import clone.swaper.business.investor.InvestorsQuery;
import clone.swaper.business.investor.registration.RegisterInvestor;
import clone.swaper.infrastructure.command.Command;
import org.hamcrest.core.Is;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertThat;

public class TestInvestor {
    static String INVESTORS_URL = "/investors";
    
    public static InvestorsQuery.InvestorsBean create(TestRestTemplate restTemplate, String email, String password) {
        RegisterInvestor registerInvestor = new RegisterInvestor(email, password);
        ResponseEntity<Command.R.ValidationReport> investorCreatedResponse = restTemplate.postForEntity(INVESTORS_URL, registerInvestor, Command.R.ValidationReport.class);
        assertThat(investorCreatedResponse.getStatusCode(), Is.is(HttpStatus.CREATED));
        
        return restTemplate.getForEntity(INVESTORS_URL, InvestorsQuery.InvestorsBean.class).getBody();
    }
    
}
