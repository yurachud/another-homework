package clone.swaper.business.loan.repayment;

import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class LoanRepaymentQuery implements Command<LoanRepaymentQuery.LoanRepaymentsBean> {
    @Override
    public Collection<TxFlag> txFlags() {
        return Lists.newArrayList(TxFlag.READ_ONLY);
    }
    
    public static class LoanRepaymentsBean implements Command.R {
        public List<LoanRepaymentBean> repayments;
        
        @CreatedForObjectMapper
        public LoanRepaymentsBean() {
        }
        
        public LoanRepaymentsBean(List<LoanRepaymentBean> repayments) {
            this.repayments = repayments;
        }
    }
}
