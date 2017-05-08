package clone.swaper.business.loan.repayment;

import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanRepaymentQueryReaction implements Reaction<LoanRepaymentQuery, LoanRepaymentQuery.LoanRepaymentsBean> {
    
    private Repository<LoanRepayment, LoanRepaymentId> repayments;
    
    public LoanRepaymentQueryReaction(Repository<LoanRepayment, LoanRepaymentId> repayments) {
        this.repayments = repayments;
    }
    
    @Override
    public LoanRepaymentQuery.LoanRepaymentsBean on(LoanRepaymentQuery $) {
        List<LoanRepaymentBean> repaymentBeans = repayments.all()
                .stream()
                .map(this::repaymentBean)
                .collect(Collectors.toList());
        return new LoanRepaymentQuery.LoanRepaymentsBean(repaymentBeans);
    }
    
    private LoanRepaymentBean repaymentBean(LoanRepayment loanRepayment) {
        return new LoanRepaymentBean(loanRepayment.amount());
    }
    
}
