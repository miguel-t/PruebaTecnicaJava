package sat.recruitment.api.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sat.recruitment.api.core.exception.ApiServerException;

@ControllerAdvice
@Slf4j
public class SatExecptionHandler {

    @ResponseBody
    @ExceptionHandler(ApiServerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> apiServerException(ApiServerException ex) {

        log.error("Error ApiServerException {}" ,ex.toString());
        return new ResponseEntity<>(ex.getErrorMessage(),ex.getStatusCode());
    }
}
