package clone.swaper.infrastructure.command;

import com.google.common.reflect.TypeToken;

public interface Reaction<C extends Command, R extends Command.R> {
    R on(C $);
    
    default TypeToken<C> commandType() {
        return new TypeToken<C>(getClass()) {
        };
    }
    
    default R fallback(C $) {
        throw new UnsupportedOperationException("No fallback available.");
    }
}