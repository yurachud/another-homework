package clone.swaper.business.loan.repayment;

import clone.swaper.business.loan.Loan;
import clone.swaper.business.loan.LoanId;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

import static clone.swaper.business.loan.LoanExpressions.with;

@Component
class RegisterLoanRepaymentReaction implements Reaction<RegisterLoanRepayment, Command.R.Void> {
    
    private final Repository<LoanRepayment, LoanRepaymentId> loanRepayments;
    private final Repository<Loan, LoanId> loans;
    
    public RegisterLoanRepaymentReaction(Repository<LoanRepayment, LoanRepaymentId> loanRepayments,
                                         Repository<Loan, LoanId> loans) {
        this.loanRepayments = loanRepayments;
        this.loans = loans;
    }
    
    @Override
    public Command.R.Void on(RegisterLoanRepayment $) {
        LoanRepayment loanRepayment = new LoanRepayment($.company, $.externalLoanId, $.externalRepaymentId, $.amount);
        loanRepayments.add(loanRepayment);
        Loan loan = loans.unique(with($.externalLoanId));
        loan.repay($.amount);
        return new Command.R.Void();
    }
    
    
}