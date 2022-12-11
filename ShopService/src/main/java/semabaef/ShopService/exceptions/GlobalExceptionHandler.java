package semabaef.ShopService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestApplicationException.class)
    public ResponseEntity<ExceptionDTO> handleReactiveWebException(RestApplicationException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setCode(ex.getCode());
        exceptionDTO.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.valueOf(ex.getCode()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDTO> handleIllegalArgumentException(HttpMessageNotReadableException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setCode(404);
        exceptionDTO.setMessage(ExceptionDescription.INVALID_REQUEST_ARGUMENTS.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

}
