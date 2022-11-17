package sat.recruitment.api.core.exception;

import lombok.Getter;

@Getter
public  abstract class ApiAbstractException extends RuntimeException {

    private final String errorMessage;

    /**
     *
     * @param errorCode
     * @param errorMessage
     */
    public ApiAbstractException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

}
