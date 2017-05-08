package clone.swaper.infrastructure.command.validation;

import java.util.List;

public class CommandNotValid extends RuntimeException {
    private List<InvalidProperty> properties;
    
    CommandNotValid(List<InvalidProperty> properties) {
        this.properties = properties;
    }
    
    public List<InvalidProperty> properties() {
        return properties;
    }
    
}
