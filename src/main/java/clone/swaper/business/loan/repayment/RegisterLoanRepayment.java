package clone.swaper.business.loan.repayment;

import clone.swaper.business.Money;
import clone.swaper.business.loan.Company;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;

public class RegisterLoanRepayment implements Command<Command.R.Void> {
    public Company company;
    public String externalLoanId;
    public String externalRepaymentId;
    public Money amount;
    
    @CreatedForObjectMapper
    public RegisterLoanRepayment() {
    }
    
    public RegisterLoanRepayment(Company company, String externalLoanId, String externalRepaymentId, Money amount) {
        this.company = company;
        this.externalLoanId = externalLoanId;
        this.externalRepaymentId = externalRepaymentId;
        this.amount = amount;
    }
}
