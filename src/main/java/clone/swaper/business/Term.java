package clone.swaper.business;

import clone.swaper.infrastructure.json.CreatedForObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Term {
    private Integer value;
    private Unit unit;
    
    public Term(Integer value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }
    
    @CreatedForObjectMapper
    public Term() {
    }
    
    public static Term months(Integer value) {
        return new Term(value, Unit.MONTH);
    }
    
    public Integer value() {
        return value;
    }
    
    public Unit unit() {
        return unit;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .add("unit", unit)
                .toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return Objects.equal(value, term.value) &&
                unit == term.unit;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(value, unit);
    }
    
    public enum Unit {
        MONTH
    }
}
