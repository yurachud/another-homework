package clone.swaper.business.investor.registration;

import clone.swaper.infrastructure.command.validation.Validatable;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

class Email implements Validatable {
    private static final String LOCAL_PART = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*";
    private static final String AT_SIGN = "@";
    private static final String HOSTNAME_FOLLOWED_BY_DOT = "(?:[a-zA-Z0-9-]+\\.)+";
    private static final String COUNTRY_CODE = "[a-zA-Z]{2,6}";
    private static final String END_OF_LINE = "$";
    private static final Pattern RFC_5322 = compile(
            LOCAL_PART + AT_SIGN + HOSTNAME_FOLLOWED_BY_DOT + COUNTRY_CODE + END_OF_LINE);
    private final String text;
    
    Email(String text) {
        this.text = text;
    }
    
    @Override
    public String text() {
        return text;
    }
    
    @Override
    public String toString() {
        return text;
    }
    
    boolean isValid() {
        return RFC_5322.asPredicate().test(text);
    }
    
    boolean isEmpty() {
        return StringUtils.isEmpty(text);
    }
    
    interface Uniqueness {
        boolean isGuaranteed(Email email);
    }
}