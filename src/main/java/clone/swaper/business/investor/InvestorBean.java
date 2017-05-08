package clone.swaper.business.investor;

import clone.swaper.infrastructure.json.CreatedForObjectMapper;

import java.util.UUID;

public class InvestorBean {
    public UUID id;
    public String email;
    
    @CreatedForObjectMapper
    public InvestorBean() {
    }
    
    public InvestorBean(UUID id, String email) {
        this.id = id;
        this.email = email;
    }
}