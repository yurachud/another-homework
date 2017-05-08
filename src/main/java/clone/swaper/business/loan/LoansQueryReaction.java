package clone.swaper.business.loan;

import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static clone.swaper.business.loan.LoanExpressions.availableForSale;

@Component
class LoansQueryReaction implements Reaction<LoansQuery, LoansQuery.LoansBean> {
    
    private final Repository<Loan, LoanId> loans;
    
    public LoansQueryReaction(Repository<Loan, LoanId> loans) {
        this.loans = loans;
    }
    
    @Override
    public LoansQuery.LoansBean on(LoansQuery $) {
        List<LoanBean> loanBeans = loans.all(availableForSale())
                .stream()
                .map(this::loanBean)
                .collect(Collectors.toList());
        return new LoansQuery.LoansBean(loanBeans);
    }
    
    private LoanBean loanBean(Loan loan) {
        return new LoanBean(loan.typesafeId().uuid(), loan.company(), loan.amount(), loan.term(), loan.status(), loan.openAmount());
    }
    
    
}