package clone.swaper.business.share;

import clone.swaper.business.Money;
import clone.swaper.business.investor.InvestorId;
import clone.swaper.business.loan.LoanId;
import clone.swaper.infrastructure.persistence.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "SHARES")
class Share extends DomainEntity<ShareId> {
    
    private InvestorId investorId;
    private LoanId loanId;
    private BigDecimal amount;
    private Money.Currency currency;
    
    public Share() {
    }
    
    public Share(InvestorId investorId,
                 LoanId loanId,
                 Money amount) {
        
        this.investorId = investorId;
        this.loanId = loanId;
        this.amount = amount.value();
        this.currency = amount.currency();
    }
    
    @Override
    protected ShareId typesafeId() {
        return new ShareId(id);
    }
    
    public InvestorId investorId() {
        return investorId;
    }
    
    public LoanId loanId() {
        return loanId;
    }
    
    public Money amount() {
        return new Money(amount, currency);
    }
}
