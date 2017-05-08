package clone.swaper.business.investor.registration;

import clone.swaper.business.investor.Investor;
import clone.swaper.business.investor.InvestorId;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static clone.swaper.business.investor.InvestorExpressions.with;


@Component
class AmongUsers implements Email.Uniqueness {
    private Repository<Investor, InvestorId> investors;
    
    @Autowired
    public AmongUsers(Repository<Investor, InvestorId> investors) {
        this.investors = investors;
    }
    
    @Override
    public boolean isGuaranteed(Email email) {
        return !investors.uniqueOptional(with(email.text())).isPresent();
    }
}