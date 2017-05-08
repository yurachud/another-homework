package clone.swaper.business.investor.registration;

import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.json.CreatedForObjectMapper;
import com.google.common.base.Joiner;

public class RegisterInvestor implements Command<Command.R.Void> {
    String email;
    String password;
    
    @CreatedForObjectMapper
    public RegisterInvestor() {
    }
    
    public RegisterInvestor(String email,
                            String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public String toLogString() {
        return Joiner
                .on(' ')
                .join(email, "********");
    }
    
}
