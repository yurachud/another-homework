package clone.swaper.business.investor.registration;

import clone.swaper.infrastructure.command.Validator;
import clone.swaper.infrastructure.command.validation.ErrorCode;
import clone.swaper.infrastructure.command.validation.FailFastValidator;
import org.springframework.stereotype.Component;

@Component
class RegisterInvestorValidator implements Validator<RegisterInvestor> {
    private final Email.Uniqueness uniqueness;
    
    public RegisterInvestorValidator(Email.Uniqueness uniqueness) {
        this.uniqueness = uniqueness;
    }
    
    @Override
    public void validate(RegisterInvestor $) {
        Email email = new Email($.email);
        Password password = new Password($.password);
        FailFastValidator validator = new FailFastValidator();
        validator
                .collect()
                .when(() -> email, Email::isEmpty).then(ErrorCode.REQUIRED)
                .whenNot(() -> email, Email::isValid).then(ErrorCode.WRONG_PATTERN)
                .whenNot(() -> email, uniqueness::isGuaranteed).then(ErrorCode.EXISTS)
                .when(() -> password, Password::isEmpty).then(ErrorCode.REQUIRED)
                .when(() -> password, new TooShort()).then(ErrorCode.TOO_SHORT)
                .when(() -> password, new LettersOnly()).then(ErrorCode.SHOULD_CONTAIN_NUMBER)
                .throwAll();
    }
    
}
