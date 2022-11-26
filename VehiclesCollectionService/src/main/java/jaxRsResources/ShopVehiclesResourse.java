package jaxRsResources;

import exceptions.ExceptionDescription;
import exceptions.HttpApplicationException;
import models.enums.VehicleType;
import services.ShopVehiclesService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
@Path("/vehicles")
public class ShopVehiclesResourse {
    @EJB
    private ShopVehiclesService shopVehiclesService;


    @GET
    @Path("/search/by-type/{type}")
    public Response searchVehiclesByType(@PathParam("type") String typeString) {
        VehicleType type;
        try {
            type = Enum.valueOf(VehicleType.class, typeString);
        } catch (Exception e){
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        }
        return Response.ok(shopVehiclesService.searchVehiclesByType(type)).build();
    }


    @GET
    @Path("/search/by-engine-power/{from}/{to}")
    public Response searchVehiclesByEnginePowerInRange(@PathParam("from") Integer min, @PathParam("to") Integer max) {
        return Response.ok(shopVehiclesService.searchVehiclesByEnginePowerInRange(min, max)).build();
    }

}
