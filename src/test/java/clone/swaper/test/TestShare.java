package clone.swaper.test;

import clone.swaper.business.Money;
import clone.swaper.business.share.BuyShare;
import clone.swaper.business.share.ShareQuery;
import clone.swaper.infrastructure.command.Command;
import org.hamcrest.core.Is;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.Assert.assertThat;

public class TestShare {
    static String SHARES_URL = "/shares";
    
    public static ShareQuery.SharesBean create(TestRestTemplate restTemplate, UUID investorId, UUID loanId, Money amount) {
        BuyShare buyShare = new BuyShare(investorId, loanId, amount);
        ResponseEntity<Command.R.Void> loanCreatedResponse = restTemplate.postForEntity(SHARES_URL, buyShare, Command.R.Void.class);
        assertThat(loanCreatedResponse.getStatusCode(), Is.is(HttpStatus.CREATED));
        return restTemplate.getForEntity(SHARES_URL, ShareQuery.SharesBean.class).getBody();
    }
    
    public static ShareQuery.SharesBean get(TestRestTemplate restTemplate) {
        return restTemplate.getForEntity(SHARES_URL, ShareQuery.SharesBean.class).getBody();
    }
}
