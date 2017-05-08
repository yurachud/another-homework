package clone.swaper.infrastructure.persistence;

public class DomainEntityNotFound extends RuntimeException {
    
    <ID extends Id> DomainEntityNotFound(ID id) {
        super("Entity not found with id: " + id);
    }
    
    DomainEntityNotFound() {
        super();
    }
}
