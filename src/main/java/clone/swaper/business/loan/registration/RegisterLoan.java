package clone.swaper.business.loan.registration;

import clone.swaper.business.Money;
import clone.swaper.business.Term;
import clone.swaper.business.loan.Company;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;
import com.google.common.base.MoreObjects;

public class RegisterLoan implements Command<Command.R.Void> {
    String externalId;
    Money amount;
    Term term;
    Company company;
    
    public RegisterLoan(Company company,
                        String externalId,
                        Money amount,
                        Term term) {
        this.externalId = externalId;
        this.amount = amount;
        this.term = term;
        this.company = company;
    }
    
    @CreatedForObjectMapper
    public RegisterLoan() {
    }
    
    @Override
    public String toLogString() {
        return MoreObjects.toStringHelper(this)
                .add("externalId", externalId)
                .add("amount", amount)
                .add("term", term)
                .add("company", company)
                .toString();
    }
}
