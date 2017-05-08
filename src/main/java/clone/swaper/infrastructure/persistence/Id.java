package clone.swaper.infrastructure.persistence;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Function;

public abstract class Id implements Serializable {
    private UUID uuid;
    
    public Id(UUID uuid) {
        this.uuid = uuid;
    }
    
    public Id(Id id) {
        this.uuid = id.uuid();
    }
    
    public UUID uuid() {
        return uuid;
    }
    
    public <T> T as(Function<Id, T> convert) {
        return convert.apply(this);
    }
    
    @Override
    public String toString() {
        return uuid.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null
                || getClass() != o.getClass()) {
            return false;
        }
        Id id = (Id) o;
        return Objects.equal(uuid, id.uuid);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
