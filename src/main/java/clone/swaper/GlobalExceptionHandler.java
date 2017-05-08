package clone.swaper;

import clone.swaper.infrastructure.command.Command;
import clone.swaper.infrastructure.command.validation.CommandNotValid;
import clone.swaper.infrastructure.persistence.DomainEntityNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;

@ControllerAdvice
@RestController
class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    String handleRuntimeException(RuntimeException e) {
        log.error("Internal server Error", e);
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = DomainEntityNotFound.class)
    String handleDomainEntityNotFound(DomainEntityNotFound e) {
        log.error("Entity not found", e);
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = EntityExistsException.class)
    String handleDomainEntityExists(EntityExistsException e) {
        log.error("Entity already exists", e);
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CommandNotValid.class)
    Command.R.ValidationReport handleInvalidRequest(CommandNotValid e) {
        log.error("Validation error {}", e.properties());
        Command.R.ValidationReport validationReport = new Command.R.ValidationReport();
        validationReport.addAll(e.properties());
        return validationReport;
    }
    
}  