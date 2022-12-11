package exceptions.exceptionMappers;

import exceptions.ExceptionDTO;
import exceptions.RestApplicationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestApplicationExceptionMapper implements ExceptionMapper<RestApplicationException> {
    @Override
    public Response toResponse(RestApplicationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setCode(exception.getCode());
        exceptionDTO.setMessage(exception.getMessage());
        return Response.status(exception.getCode())
                .entity(exceptionDTO)
                .type(MediaType.APPLICATION_XML_TYPE)
                .build();
    }

}
