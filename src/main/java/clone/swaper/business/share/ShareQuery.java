package clone.swaper.business.share;

import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;

import java.util.List;

public class ShareQuery implements Command<ShareQuery.SharesBean> {
    
    public static class SharesBean implements Command.R {
        public List<ShareBean> shares;
        
        @CreatedForObjectMapper
        public SharesBean() {
        }
        
        public SharesBean(List<ShareBean> shares) {
            this.shares = shares;
        }
    }
}
