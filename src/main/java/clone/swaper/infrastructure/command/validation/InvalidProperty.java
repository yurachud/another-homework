package clone.swaper.infrastructure.command.validation;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class InvalidProperty {
    String property;
    String invalidValue;
    ErrorCode errorCode;
    
    public InvalidProperty() {
    }
    
    public InvalidProperty(String propertyName, String wrongValue, ErrorCode errorCode) {
        this.property = propertyName;
        this.invalidValue = wrongValue;
        this.errorCode = errorCode;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("property", property)
                .add("invalidValue", invalidValue)
                .add("errorCode", errorCode)
                .toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvalidProperty that = (InvalidProperty) o;
        return Objects.equal(property, that.property) &&
                Objects.equal(invalidValue, that.invalidValue) &&
                errorCode == that.errorCode;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(property, invalidValue, errorCode);
    }
}
