package clone.swaper.business.share;

import clone.swaper.infrastructure.command.Now;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class ShareController {
    
    private final Now now;
    
    ShareController(Now now) {
        this.now = now;
    }
    
    @PostMapping("/shares")
    @ResponseStatus(HttpStatus.CREATED)
    void buyShare(@RequestBody BuyShare buyShare) {
        buyShare.execute(now);
    }
    
    @GetMapping("/shares")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<ShareQuery.SharesBean> get() {
        ShareQuery.SharesBean sharesBean = new ShareQuery().execute(now);
        return ResponseEntity.ok(sharesBean);
    }
}