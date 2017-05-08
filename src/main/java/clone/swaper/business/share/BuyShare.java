package clone.swaper.business.share;

import clone.swaper.business.Money;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;

import java.util.UUID;

public class BuyShare implements Command<Command.R.Void> {
    public UUID investorId;
    public UUID loanId;
    public Money amount;
    
    @CreatedForObjectMapper
    public BuyShare() {
    }
    
    public BuyShare(UUID investorId,
                    UUID loanId,
                    Money amount) {
        this.investorId = investorId;
        this.loanId = loanId;
        this.amount = amount;
    }
}
