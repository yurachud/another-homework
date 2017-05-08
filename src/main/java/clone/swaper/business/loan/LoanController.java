package clone.swaper.business.loan;

import clone.swaper.business.loan.registration.RegisterLoan;
import clone.swaper.infrastructure.command.Now;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class LoanController {
    
    private final Now now;
    
    LoanController(Now now) {
        this.now = now;
    }
    
    @PostMapping("/loans")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@RequestBody RegisterLoan registerLoan) {
        registerLoan.execute(now);
    }
    
    @GetMapping("/loans")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<LoansQuery.LoansBean> get() {
        LoansQuery.LoansBean loansBean = new LoansQuery().execute(now);
        return ResponseEntity.ok(loansBean);
    }
}