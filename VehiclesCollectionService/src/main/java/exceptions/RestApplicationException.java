package exceptions;

import lombok.Data;

@Data
public class RestApplicationException extends RuntimeException {
    private final Integer code;
    private final String message;

    public RestApplicationException(ExceptionDescription exceptionDescription) {
        this.code = exceptionDescription.getCode();
        this.message = exceptionDescription.getMessage();
    }

}
