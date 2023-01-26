package jaxRsResources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
@Path("/test")
public class TestResource {

    @GET
    public Response tellMeAreYouLive() {
        return Response.ok().build();
    }

}
