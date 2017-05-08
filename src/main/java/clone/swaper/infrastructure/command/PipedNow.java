package clone.swaper.infrastructure.command;

import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Component
class PipedNow implements Now {
    private final Reactions reactions;
    private final Validators validators;
    private final PlatformTransactionManager txManager;
    
    public PipedNow(Reactions reactions,
                    Validators validators,
                    PlatformTransactionManager txManager) {
        this.reactions = reactions;
        this.validators = validators;
        this.txManager = txManager;
    }
    
    @Override
    public <C extends Command<R>, R extends Command.R> R execute(C command) {
        Now pipe =
                new Loggable(
                        new Validatable(
                                new HystrixGuarded(
                                        new Transactional(
                                                new Reacting()))));
        return pipe.execute(command);
    }
    
    private class Loggable implements Now {
        private final Now origin;
        
        Loggable(Now origin) {
            this.origin = origin;
        }
        
        @Override
        public <C extends Command<R>, R extends Command.R> R execute(C command) {
            Logger log = LoggerFactory.getLogger(command.getClass());
            log.info(">>> {}", command.toLogString());
            R response = origin.execute(command);
            log.info("<<< {}", response.toLogString());
            return response;
        }
    }
    
    private class HystrixGuarded implements Now {
        
        private Now origin;
        
        public HystrixGuarded(Now origin) {
            this.origin = origin;
        }
        
        @Override
        public <C extends Command<R>, R extends Command.R> R execute(C command) {
            Reaction<C, R> reaction = reactions.by(command);
            HystrixCommand.Setter commandOptions = HystrixCommandOptions.options(command);
            HystrixCommand<R> hystrixCommand = new HystrixCommand<R>(commandOptions) {
                @Override
                protected R run() throws Exception {
                    return origin.execute(command);
                }
                
                @Override
                protected R getFallback() {
                    return reaction.fallback(command);
                }
            };
            return hystrixCommand.execute();
        }
        
    }
    
    private class Validatable implements Now {
        private final Now origin;
        
        Validatable(Now origin) {
            this.origin = origin;
        }
        
        @Override
        public <C extends Command<R>, R extends Command.R> R execute(C command) {
            Validator<C> validator = validators.by(command);
            validator.validate(command);
            return origin.execute(command);
        }
    }
    
    private class Transactional implements Now {
        private final Now origin;
        
        Transactional(Now origin) {
            this.origin = origin;
        }
        
        @Override
        public <C extends Command<R>, R extends Command.R> R execute(C command) {
            TransactionTemplate tx = new TransactionTemplate(txManager);
            command.txFlags().forEach(flag -> flag.apply(tx));
            return tx.execute(txStatus -> origin.execute(command));
        }
    }
    
    private class Reacting implements Now {
        @Override
        public <C extends Command<R>, R extends Command.R> R execute(C command) {
            Reaction<C, R> reaction = reactions.by(command);
            return reaction.on(command);
        }
    }
}