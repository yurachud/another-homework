package clone.swaper.business.investor;

import clone.swaper.infrastructure.persistence.Id;

import java.util.UUID;

public class InvestorId extends Id {
    
    public InvestorId(UUID uuid) {
        super(uuid);
    }
    
    public InvestorId(Id id) {
        super(id);
    }
}
