package exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static exceptions.ExceptionDescription.INVALID_REQUEST_ARGUMENTS;

@Provider
public class InvalidRequestArgumentsException implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(INVALID_REQUEST_ARGUMENTS).build();
    }

}
