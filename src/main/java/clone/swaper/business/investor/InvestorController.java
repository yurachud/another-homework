package clone.swaper.business.investor;

import clone.swaper.business.investor.registration.RegisterInvestor;
import clone.swaper.infrastructure.command.Now;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class InvestorController {
    
    private final Now now;
    
    InvestorController(Now now) {
        this.now = now;
    }
    
    @PostMapping("/investors")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@RequestBody RegisterInvestor registerInvestor) {
        registerInvestor.execute(now);
    }
    
    @GetMapping("/investors")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<InvestorsQuery.InvestorsBean> all() {
        return ResponseEntity.ok(new InvestorsQuery().execute(now));
    }
}