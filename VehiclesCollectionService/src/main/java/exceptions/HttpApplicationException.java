package exceptions;

import lombok.Data;

@Data
public class HttpApplicationException extends RuntimeException {
    private final Integer code;
    private final String message;

    public HttpApplicationException(ExceptionDescription exceptionDescription) {
        this.code = exceptionDescription.getCode();
        this.message = exceptionDescription.getMessage();
    }

}
