package clone.swaper.business.investor.registration;

import clone.swaper.infrastructure.command.validation.Validatable;
import org.springframework.util.StringUtils;

class Password implements Validatable {
    String password;
    
    Password(String password) {
        this.password = password;
    }
    
    @Override
    public String text() {
        return password;
    }
    
    boolean isEmpty() {
        return StringUtils.isEmpty(password);
    }
}