package exceptions.exceptionMappers;

import exceptions.HttpApplicationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HttpApplicationExceptionMapper implements ExceptionMapper<HttpApplicationException> {

    @Override
    public Response toResponse(HttpApplicationException exception) {
        return Response.status(exception.getCode())
                .entity(exception.getMessage())
                .type(MediaType.APPLICATION_XML_TYPE)
                .build();
    }

}
