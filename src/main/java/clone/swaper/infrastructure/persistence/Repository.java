package clone.swaper.infrastructure.persistence;

import com.querydsl.core.types.Predicate;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID extends Id> {
    T unique(ID id);
    
    T unique(Predicate predicate);
    
    Optional<T> uniqueOptional(ID id);
    
    Optional<T> uniqueOptional(Predicate predicate);
    
    List<T> all(Predicate predicate);
    
    List<T> all();
    
    void add(T entity);
    
    void remove(T entity);
    
    long count(Predicate predicate);
    
    long count();
    
    void removeAll();
}
