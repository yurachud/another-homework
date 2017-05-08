package clone.swaper.infrastructure.command;

import java.lang.reflect.Type;

class NoReactionFound extends RuntimeException {
    
    NoReactionFound(Type commandType) {
        super("No reaction found for command of type: " + commandType);
    }
}
