package clone.swaper.business.loan.repayment;

import clone.swaper.business.Money;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;

public class LoanRepaymentBean implements Command.R {
    public Money amount;
    
    @CreatedForObjectMapper
    public LoanRepaymentBean() {
    }
    
    public LoanRepaymentBean(Money amount) {
        this.amount = amount;
    }
}