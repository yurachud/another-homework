package clone.swaper.business.loan.repayment;

import clone.swaper.infrastructure.persistence.Id;

import java.util.UUID;

public class LoanRepaymentId extends Id {
    public LoanRepaymentId(UUID uuid) {
        super(uuid);
    }
    
    public LoanRepaymentId(Id id) {
        super(id);
    }
}
