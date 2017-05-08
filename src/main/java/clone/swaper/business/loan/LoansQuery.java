package clone.swaper.business.loan;

import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class LoansQuery implements Command<LoansQuery.LoansBean> {
    
    @Override
    public Collection<TxFlag> txFlags() {
        return Lists.newArrayList(TxFlag.READ_ONLY);
    }
    
    public static class LoansBean implements Command.R {
        public List<LoanBean> loans;
        
        @CreatedForObjectMapper
        public LoansBean() {
        }
        
        public LoansBean(List<LoanBean> loans) {
            this.loans = loans;
        }
    }
}
