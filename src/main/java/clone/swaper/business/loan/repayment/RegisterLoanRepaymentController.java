package clone.swaper.business.loan.repayment;

import clone.swaper.infrastructure.command.Now;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
class RegisterLoanRepaymentController {
    
    private final Now now;
    
    RegisterLoanRepaymentController(Now now) {
        this.now = now;
    }
    
    @PostMapping("/repayments")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@RequestBody RegisterLoanRepayment registerLoanRepayment) {
        registerLoanRepayment.execute(now);
    }
    
    @GetMapping("/repayments")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<LoanRepaymentQuery.LoanRepaymentsBean> all() {
        LoanRepaymentQuery.LoanRepaymentsBean loanRepaymentsBean = new LoanRepaymentQuery().execute(now);
        return ResponseEntity.ok(loanRepaymentsBean);
    }
}