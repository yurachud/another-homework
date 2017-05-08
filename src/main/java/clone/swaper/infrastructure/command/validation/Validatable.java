package clone.swaper.infrastructure.command.validation;

public interface Validatable {
    default String propertyName() {
        return this.getClass().getSimpleName();
    }
    
    String text();
    
}
