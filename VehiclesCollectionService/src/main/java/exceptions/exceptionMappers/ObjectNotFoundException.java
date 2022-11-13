package exceptions.exceptionMappers;

import exceptions.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static exceptions.ExceptionDescription.INVALID_REQUEST_ARGUMENTS;

public class ObjectNotFoundException implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(INVALID_REQUEST_ARGUMENTS).build();

    }
}
