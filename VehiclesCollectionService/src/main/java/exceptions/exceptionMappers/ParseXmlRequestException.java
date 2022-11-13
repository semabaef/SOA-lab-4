package exceptions.exceptionMappers;

import exceptions.ParseXmlException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static exceptions.ExceptionDescription.INVALID_REQUEST_ARGUMENTS;

public class ParseXmlRequestException implements ExceptionMapper<ParseXmlException> {
    @Override
    public Response toResponse(ParseXmlException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(INVALID_REQUEST_ARGUMENTS).build();
    }
}
