package sat.recruitment.api.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiServerException extends ApiAbstractException {

    private final HttpStatus statusCode;

    public ApiServerException( String errorMessage,HttpStatus statusCode) {
        super(errorMessage);
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        String message =  this.getErrorMessage();

        if (this.getCause() != null) {
            message += " " + this.getCause().toString();
        }
        return message;
    }
}
