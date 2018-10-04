package no.difa.eik.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class CustomMessageResponseBuilder extends ExceptionHandlerHelper{

    private MethodArgumentTypeMismatchException exception;

    public CustomMessageResponseBuilder(
            MethodArgumentTypeMismatchException exception,
            String message,
            HttpStatus status) {
        super(exception, message, status);
        this.exception = exception;
    }


    @Override
    public ResponseEntity<Object> buildErrorResponse()
    {

        this.error.setMessage(
                String.format(
                        "The parameter '%s' of value '%s' could not be converted to type '%s'",
                        this.exception.getName(), this.exception.getValue(), this.exception.getRequiredType().getSimpleName()));
        this.error.setDebugMessage(this.exception.getMessage());
        return super.buildErrorResponse();
    }



}
