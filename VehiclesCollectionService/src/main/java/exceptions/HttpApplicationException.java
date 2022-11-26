package exceptions;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;

import javax.ws.rs.WebApplicationException;

@ToString
public class HttpApplicationException extends WebApplicationException {
    private final Integer code;
    private final String message;

@SneakyThrows
    public HttpApplicationException(ExceptionDescription exceptionDescription) {
        this.code = exceptionDescription.getCode();
        this.message = exceptionDescription.getMessage();
    }

}
