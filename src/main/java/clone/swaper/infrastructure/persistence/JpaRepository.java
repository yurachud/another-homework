package clone.swaper.infrastructure.persistence;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.repository.support.JpaEntityInformationSupport.getEntityInformation;

public class JpaRepository<T, ID extends Id> extends QueryDslJpaRepository<T, UUID> implements Repository<T, ID> {
    private final EntityManager entityManager;
    
    public JpaRepository(EntityManager entityManager, Class<T> entityType) {
        super((JpaEntityInformation<T, UUID>) getEntityInformation(entityType, entityManager), entityManager);
        this.entityManager = entityManager;
    }
    
    @Override
    public T unique(ID id) {
        return uniqueOptional(id)
                .orElseThrow(() -> new DomainEntityNotFound(id));
    }
    
    @Override
    public T unique(Predicate predicate) {
        return uniqueOptional(predicate)
                .orElseThrow(DomainEntityNotFound::new);
    }
    
    @Override
    public Optional<T> uniqueOptional(ID id) {
        return Optional.ofNullable(findOne(id.uuid()));
    }
    
    @Override
    public Optional<T> uniqueOptional(Predicate predicate) {
        return Optional.ofNullable(findOne(predicate));
    }
    
    @Override
    public List<T> all(Predicate predicate) {
        return findAll(predicate);
    }
    
    @Override
    public List<T> all() {
        return findAll();
    }
    
    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }
    
    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }
    
    @Override
    public void removeAll() {
        all().forEach(this::remove);
    }
    
}