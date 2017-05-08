package clone.swaper.infrastructure.persistence;

import com.google.common.base.Objects;
import org.hibernate.annotations.OptimisticLock;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class DomainEntity<ID extends Id> {
    
    @javax.persistence.Id
    protected UUID id = UUID.randomUUID();
    
    @OptimisticLock(excluded = true)
    @Column(nullable = false)
    protected LocalDateTime created = LocalDateTime.now();
    
    @OptimisticLock(excluded = true)
    @Column(nullable = false)
    protected LocalDateTime updated = LocalDateTime.now();
    
    @Column(nullable = false)
    protected Long version = 0L;
    
    protected abstract ID typesafeId();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DomainEntity<?> that = (DomainEntity<?>) obj;
        return Objects.equal(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
}