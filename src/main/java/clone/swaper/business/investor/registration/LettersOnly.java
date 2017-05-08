package clone.swaper.business.investor.registration;

import com.google.common.base.CharMatcher;

import java.util.function.Predicate;

class LettersOnly implements Predicate<Password> {
    @Override
    public boolean test(Password pw) {
        return CharMatcher.digit().matchesNoneOf(pw.text());
    }
}