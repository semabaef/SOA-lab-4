package exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;

import javax.ws.rs.WebApplicationException;

@Data
public class HttpApplicationException extends RuntimeException {
    private final Integer code;
    private final String message;

@SneakyThrows
    public HttpApplicationException(ExceptionDescription exceptionDescription) {
        this.code = exceptionDescription.getCode();
        this.message = exceptionDescription.getMessage();
    }

}
