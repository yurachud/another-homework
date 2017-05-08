package clone.swaper.business.loan;

import clone.swaper.business.Money;
import clone.swaper.business.Term;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;

import java.util.UUID;

public class LoanBean implements Command.R {
    public Company company;
    public Money amount;
    public Term term;
    public Loan.Status status;
    public Money openAmount;
    public UUID id;
    
    @CreatedForObjectMapper
    public LoanBean() {
    }
    
    public LoanBean(UUID id, Company company, Money amount, Term term, Loan.Status status, Money openAmount) {
        this.id = id;
        this.company = company;
        this.amount = amount;
        this.term = term;
        this.status = status;
        this.openAmount = openAmount;
    }
}