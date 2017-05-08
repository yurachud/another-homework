package clone.swaper.infrastructure.command;

import clone.swaper.infrastructure.command.validation.InvalidProperty;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface Command<T extends Command.R> {
    
    default T execute(Now now) {
        return now.execute(this);
    }
    
    default Type type() {
        return this.getClass();
    }
    
    default String toLogString() {
        return "";
    }
    
    default Collection<TxFlag> txFlags() {
        return Collections.emptyList();
    }
    
    default Optional<Integer> concurrencyLimit() {
        return Optional.empty();
    }
    
    default String name() {
        return getClass().getCanonicalName();
    }
    
    default int executionTimeout() {
        return 1000;
    }
    
    interface TxFlag {
        TxFlag READ_ONLY = tx -> tx.setReadOnly(true);
        
        void apply(TransactionTemplate tx);
    }
    
    interface R {
        default String toLogString() {
            return "";
        }
        
        class Void implements Command.R {
        }
        
        class ValidationReport implements Command.R {
            private List<InvalidProperty> properties = Collections.emptyList();
            
            public ValidationReport() {
            }
            
            public void addAll(List<InvalidProperty> properties) {
                this.properties = properties;
            }
            
            public List<InvalidProperty> properties() {
                return properties;
            }
        }
    }
}