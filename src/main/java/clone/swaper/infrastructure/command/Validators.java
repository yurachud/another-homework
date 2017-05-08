package clone.swaper.infrastructure.command;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

@Component
class Validators {
    private static final Logger log = LoggerFactory.getLogger(Validators.class);
    private final LoadingCache<Type, Validator> cachedValidators;
    
    @Autowired
    Validators(ListableBeanFactory beanFactory) {
        this.cachedValidators = Caffeine.newBuilder()
                .build(commandType -> {
                            Optional<Validator> validatorOptional = validators(beanFactory)
                                    .stream()
                                    .filter(validator -> validator.commandType().isSupertypeOf(commandType))
                                    .findFirst();
                            if (!validatorOptional.isPresent()) {
                                log.warn("No validator found for command of type: {}", commandType);
                                return (Validator) command -> {
                                };
                            }
                            return validatorOptional.get();
                        }
                );
    }
    
    <C extends Command<R>, R extends Command.R> Validator<C> by(C command) {
        return cachedValidators.get(command.type());
    }
    
    private Collection<Validator> validators(ListableBeanFactory beanFactory) {
        return beanFactory.getBeansOfType(Validator.class).values();
    }
}