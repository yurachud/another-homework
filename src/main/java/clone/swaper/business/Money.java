package clone.swaper.business;

import clone.swaper.infrastructure.json.CreatedForObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private BigDecimal value;
    private Currency currency;
    
    public Money(BigDecimal value, Currency currency) {
        this.value = value.setScale(2, RoundingMode.HALF_EVEN);
        this.currency = currency;
    }
    
    @CreatedForObjectMapper
    public Money() {
    }
    
    public static Money eur(BigDecimal amount) {
        return new Money(amount, Currency.EUR);
    }
    
    public static Money eur(double amount) {
        return new Money(BigDecimal.valueOf(amount), Currency.EUR);
    }
    
    public BigDecimal value() {
        return value;
    }
    
    public Currency currency() {
        return currency;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .add("currency", currency)
                .toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equal(value, money.value) &&
                currency == money.currency;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(value, currency);
    }
    
    public enum Currency {
        EUR
    }
}
