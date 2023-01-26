package jaxRsResources;

import services.VehicleService;
import utils.validators.CommonValidator;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
@Path("/vehicles")
public class VehiclesCollectionExtraOperationsResource {

    @EJB
    private VehicleService vehicleService;

    @EJB
    private CommonValidator validator;

    @GET
    @Path("/count/type")
    public Response getCountVehiclesWhereTypeIs(@QueryParam("type") String type) {
        validator.validateVehicleType(type);
        return Response.ok(vehicleService.countVehiclesWhereTypeIs(type)).build();
    }


    @GET
    @Path("/name/like")
    public Response getVehiclesWhereNameLike(@QueryParam("nameLike") String nameLike) {
        validator.validateString(nameLike);
        return Response.ok(vehicleService.getVehiclesWhereNameLike(nameLike)).build();
    }


    @GET
    @Path("/enginePowere/less")
    public Response getVehiclesWhereEnginePowerIsLessThan(@QueryParam("enginePower") String enginePowerString) {
        Integer enginePower = validator.validateInteger(enginePowerString);
        return Response.ok(vehicleService.getVehiclesWhereEnginePowerIsLessThan(enginePower)).build();
    }

}
