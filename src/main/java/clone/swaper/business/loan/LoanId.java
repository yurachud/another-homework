package clone.swaper.business.loan;

import clone.swaper.infrastructure.persistence.Id;

import java.util.UUID;

public class LoanId extends Id {
    public LoanId(UUID uuid) {
        super(uuid);
    }
    
    public LoanId(Id id) {
        super(id);
    }
}
