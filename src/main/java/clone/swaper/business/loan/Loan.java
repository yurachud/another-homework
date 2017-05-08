package clone.swaper.business.loan;

import clone.swaper.business.Money;
import clone.swaper.business.Term;
import clone.swaper.infrastructure.persistence.DomainEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "LOANS")
public class Loan extends DomainEntity<LoanId> {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Company company;
    @Column(nullable = false, unique = true)
    private String externalId;
    @Column(nullable = false)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Money.Currency currency;
    @Column(nullable = false)
    private Integer term;
    @Column(nullable = false)
    private Term.Unit termUnit;
    @Column(nullable = false)
    private Loan.Status status;
    @Column(nullable = false)
    private BigDecimal openAmount;
    
    public Loan() {
    }
    
    public Loan(Company company,
                String externalId,
                Money money,
                Term term) {
        this.company = company;
        this.externalId = externalId;
        this.amount = money.value();
        this.openAmount = money.value();
        this.status = Status.OPEN;
        this.currency = money.currency();
        this.term = term.value();
        this.termUnit = term.unit();
    }
    
    @Override
    public LoanId typesafeId() {
        return new LoanId(id);
    }
    
    public Company company() {
        return company;
    }
    
    public Money amount() {
        return new Money(amount, currency);
    }
    
    public Term term() {
        return new Term(term, termUnit);
    }
    
    public Status status() {
        return status;
    }
    
    public Money openAmount() {
        return new Money(openAmount, currency);
    }
    
    public void repay(Money amount) {
        this.openAmount = this.openAmount.subtract(amount.value());
    }
    
    public enum Status {
        OPEN, PAID
    }
}
