package clone.swaper.business.share;

import clone.swaper.business.Money;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;

import java.util.UUID;

class ShareBean {
    public UUID shareId;
    public UUID investorId;
    public UUID loanId;
    public Money amount;
    
    @CreatedForObjectMapper
    public ShareBean() {
    }
    
    public ShareBean(UUID shareId,
                     UUID investorId,
                     UUID loanId,
                     Money amount) {
        
        this.shareId = shareId;
        this.investorId = investorId;
        this.loanId = loanId;
        this.amount = amount;
    }
}
