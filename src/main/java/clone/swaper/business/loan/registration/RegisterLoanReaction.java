package clone.swaper.business.loan.registration;

import clone.swaper.business.loan.Loan;
import clone.swaper.business.loan.LoanId;
import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

@Component
class RegisterLoanReaction implements Reaction<RegisterLoan, Command.R.Void> {
    
    private final Repository<Loan, LoanId> loans;
    
    public RegisterLoanReaction(Repository<Loan, LoanId> loans) {
        this.loans = loans;
    }
    
    @Override
    public Command.R.Void on(RegisterLoan $) {
        Loan loan = new Loan($.company, $.externalId, $.amount, $.term);
        loans.add(loan);
        return new Command.R.Void();
    }
    
}