package clone.swaper.business.investor.registration;

import clone.swaper.business.investor.Investor;
import clone.swaper.business.investor.InvestorId;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

@Component
class RegisterInvestorReaction implements Reaction<RegisterInvestor, Command.R.Void> {
    
    private final Repository<Investor, InvestorId> investors;
    
    public RegisterInvestorReaction(Repository<Investor, InvestorId> investors) {
        this.investors = investors;
    }
    
    @Override
    public Command.R.Void on(RegisterInvestor $) {
        Investor investor = new Investor($.email, $.password);
        investors.add(investor);
        return new Command.R.Void();
    }
    
    
}