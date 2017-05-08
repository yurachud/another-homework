package clone.swaper.business.loan.repayment;

import clone.swaper.business.Money;
import clone.swaper.business.loan.Company;
import clone.swaper.infrastructure.persistence.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "LOAN_REPAYMENTS")
public class LoanRepayment extends DomainEntity<LoanRepaymentId> {
    private Company company;
    private String externalLoanId;
    private String externalRepaymentId;
    private BigDecimal amount;
    private Money.Currency currency;
    
    public LoanRepayment() {
    }
    
    public LoanRepayment(Company company,
                         String externalLoanId,
                         String externalRepaymentId,
                         Money amount) {
        
        this.company = company;
        this.externalLoanId = externalLoanId;
        this.externalRepaymentId = externalRepaymentId;
        this.amount = amount.value();
        this.currency = amount.currency();
    }
    
    @Override
    protected LoanRepaymentId typesafeId() {
        return new LoanRepaymentId(id);
    }
    
    public Money amount() {
        return new Money(amount, currency);
    }
}
