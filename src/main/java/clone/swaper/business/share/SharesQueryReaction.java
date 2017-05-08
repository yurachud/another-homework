package clone.swaper.business.share;

import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SharesQueryReaction implements Reaction<ShareQuery, ShareQuery.SharesBean> {
    private final Repository<Share, ShareId> shares;
    
    public SharesQueryReaction(Repository<Share, ShareId> shares) {
        this.shares = shares;
    }
    
    @Override
    public ShareQuery.SharesBean on(ShareQuery $) {
        List<ShareBean> shareBeans = shares.all()
                .stream()
                .map(this::shareBean)
                .collect(Collectors.toList());
        return new ShareQuery.SharesBean(shareBeans);
    }
    
    private ShareBean shareBean(Share share) {
        return new ShareBean(share.typesafeId().uuid(), share.investorId().uuid(), share.loanId().uuid(), share.amount());
    }
    
}
