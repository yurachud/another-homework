package clone.swaper.business.share;

import clone.swaper.business.investor.InvestorId;
import clone.swaper.business.loan.LoanId;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

@Component
public class BuyShareReaction implements Reaction<BuyShare, Command.R.Void> {
    
    private final Repository<Share, ShareId> shares;
    
    public BuyShareReaction(Repository<Share, ShareId> shares) {
        this.shares = shares;
    }
    
    @Override
    public Command.R.Void on(BuyShare $) {
        Share share = new Share(new InvestorId($.investorId), new LoanId($.loanId), $.amount);
        shares.add(share);
        return new Command.R.Void();
    }
}
