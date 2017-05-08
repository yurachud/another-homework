package clone.swaper.business.investor.registration;

import java.util.function.Predicate;

class TooShort implements Predicate<Password> {
    @Override
    public boolean test(Password pw) {
        return pw.text().length() < 7;
    }
}