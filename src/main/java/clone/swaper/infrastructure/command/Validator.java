package clone.swaper.infrastructure.command;

import com.google.common.reflect.TypeToken;

public interface Validator<C> {
    void validate(C command);
    
    default TypeToken<C> commandType() {
        return new TypeToken<C>(getClass()) {
        };
    }
}