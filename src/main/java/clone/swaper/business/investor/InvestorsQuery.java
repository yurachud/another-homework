package clone.swaper.business.investor;

import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class InvestorsQuery implements Command<InvestorsQuery.InvestorsBean> {
    
    @Override
    public Collection<TxFlag> txFlags() {
        return Lists.newArrayList(TxFlag.READ_ONLY);
    }
    
    public static class InvestorsBean implements Command.R {
        public List<InvestorBean> investors;
        
        @CreatedForObjectMapper
        public InvestorsBean() {
        }
        
        public InvestorsBean(List<InvestorBean> investors) {
            this.investors = investors;
        }
    }
    
    
}
