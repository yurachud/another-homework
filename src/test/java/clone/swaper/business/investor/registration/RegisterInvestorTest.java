package clone.swaper.business.investor.registration;

import clone.swaper.SwaperApplicationTest;
import clone.swaper.business.investor.InvestorsQuery;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.command.validation.ErrorCode;
import clone.swaper.infrastructure.command.validation.InvalidProperty;
import clone.swaper.test.TestInvestor;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertThat;

public class RegisterInvestorTest extends SwaperApplicationTest {
    
    String PASSWORD = "password1";
    String CREATE_INVESTOR_URL = "/investors";
    String VALID_EMAIL = "valid2@email.com";
    String INVALID_EMAIL = "wrong@mail";
    
    @Test
    public void registerInvestor() throws Exception {
        InvestorsQuery.InvestorsBean investorBean = TestInvestor.create(restTemplate, VALID_EMAIL, PASSWORD);
        assertThat(email(investorBean), Is.is(VALID_EMAIL));
    }
    
    @Test
    public void returnBadRequestAndValidationErrorIfWrongEmailFormat() throws Exception {
        RegisterInvestor registerInvestor = new RegisterInvestor(INVALID_EMAIL, PASSWORD);
        ResponseEntity<Command.R.ValidationReport> response = restTemplate.postForEntity(CREATE_INVESTOR_URL, registerInvestor, Command.R.ValidationReport.class);
        Command.R.ValidationReport validatedResponse = response.getBody();
        
        assertThat(response.getStatusCode(), Is.is(HttpStatus.BAD_REQUEST));
        assertThat(errorCountIn(validatedResponse), Is.is(1));
        assertThat(errorIn(validatedResponse), Is.is(new InvalidProperty(Email.class.getSimpleName(), INVALID_EMAIL, ErrorCode.WRONG_PATTERN)));
    }
    
    private int errorCountIn(Command.R.ValidationReport validatedResponse) {
        return validatedResponse.properties().size();
    }
    
    private String email(InvestorsQuery.InvestorsBean investorBean) {
        return investorBean.investors.stream().findFirst().get().email;
    }
    
    private InvalidProperty errorIn(Command.R.ValidationReport validatedResponse) {
        return validatedResponse.properties().stream().findFirst().get();
    }
}
