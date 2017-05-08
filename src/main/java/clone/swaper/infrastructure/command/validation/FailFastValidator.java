package clone.swaper.infrastructure.command.validation;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FailFastValidator {
    private final Collection<IfCondition> ifConditions = new LinkedList<>();
    
    public Validator collect() {
        return new Validator();
    }
    
    public class Validator {
        public <T extends Validatable> IfCondition whenNot(Supplier<T> value, Predicate<T> predicate) {
            return when(value, predicate.negate());
        }
        
        public <T extends Validatable> IfCondition when(Supplier<T> value, Predicate<T> predicate) {
            IfCondition<T> ifCondition = new IfCondition<>(value, predicate);
            ifConditions.add(ifCondition);
            return ifCondition;
        }
        
        public void throwFirst() {
            ifConditions.stream()
                    .filter(IfCondition::isTrue)
                    .findFirst()
                    .map(IfCondition::property)
                    .ifPresent(property -> {
                        throw new CommandNotValid(ImmutableList.of(property));
                    });
        }
        
        public void throwAll() {
            List<InvalidProperty> invalidProperties = ifConditions.stream()
                    .filter(IfCondition::isTrue)
                    .map(IfCondition::property)
                    .collect(Collectors.toList());
            if (exists(invalidProperties)) {
                throw new CommandNotValid(invalidProperties);
            }
        }
        
        private boolean exists(List<InvalidProperty> invalidProperties) {
            return !invalidProperties.isEmpty();
        }
    }
    
    public class IfCondition<T extends Validatable> {
        private final Supplier<T> item;
        private final Predicate<T> predicate;
        private ErrorCode errorCode;
        
        private IfCondition(Supplier<T> item, Predicate<T> predicate) {
            this.item = item;
            this.predicate = predicate;
        }
        
        public Validator then(ErrorCode errorCode) {
            this.errorCode = errorCode;
            return new Validator();
        }
        
        private boolean isTrue() {
            return predicate.test(item.get());
        }
        
        private InvalidProperty property() {
            Validatable validatable = item.get();
            return new InvalidProperty(validatable.propertyName(), validatable.text(), errorCode());
        }
        
        private ErrorCode errorCode() {
            return errorCode;
        }
    }
}