package clone.swaper.business.investor;

import clone.swaper.infrastructure.persistence.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INVESTORS")
public class Investor extends DomainEntity<InvestorId> {
    private String email;
    
    private String password;
    
    public Investor(String email,
                    String password) {
        this.email = email;
        this.password = password;
    }
    
    public Investor() {
    }
    
    @Override
    public InvestorId typesafeId() {
        return new InvestorId(id);
    }
    
    public String email() {
        return email;
    }
    
    public String password() {
        return password;
    }
}
