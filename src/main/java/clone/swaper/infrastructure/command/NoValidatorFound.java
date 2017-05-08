package clone.swaper.infrastructure.command;

import java.lang.reflect.Type;

class NoValidatorFound extends RuntimeException {
    NoValidatorFound(Type commandType) {
        super("No validator found for command of type: " + commandType);
    }
}
