package clone.swaper.infrastructure.command;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

@Component
class Reactions {
    private final LoadingCache<Type, Reaction> cachedReactions;
    
    @Autowired
    Reactions(ListableBeanFactory beanFactory) {
        this.cachedReactions = Caffeine.newBuilder()
                .build(commandType -> {
                            
                            Optional<Reaction> reactionOptional = reactions(beanFactory)
                                    .stream()
                                    .filter(reaction -> reaction.commandType().isSupertypeOf(commandType))
                                    .findFirst();
                            if (!reactionOptional.isPresent()) {
                                throw new NoReactionFound(commandType);
                            }
                            return reactionOptional.get();
                        }
                );
    }
    
    <C extends Command<R>, R extends Command.R> Reaction<C, R> by(C command) {
        return cachedReactions.get(command.type());
    }
    
    private Collection<Reaction> reactions(ListableBeanFactory beanFactory) {
        return beanFactory.getBeansOfType(Reaction.class).values();
    }
}