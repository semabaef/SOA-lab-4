package jaxRsResources;

import models.dto.common.VehicleFilterDTO;
import models.dto.vehicle.VehicleNoIdDTO;
import models.dto.vehicle.VehicleWithIdDTO;
import models.enums.VehicleType;
import services.VehicleService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
@Path("/vehicles")
public class VehiclesCollectionCrudResource {

    @EJB
    private VehicleService vehicleService;


    @POST
    public Response addNewVehicle(VehicleNoIdDTO vehicle) {
        vehicle.validate();
        return Response.ok(vehicleService.addNewVehicle(vehicle)).build();
    }

    @PUT
    public Response updateVehicle(VehicleWithIdDTO vehicle) {
        vehicle.validate();
        return Response.ok(vehicleService.updateVehicle(vehicle)).build();
    }


    @GET
    public Response getAllVehiclesWithFilter(@QueryParam("id") Long id,
                                             @QueryParam("name") String name,
                                             @QueryParam("x") Float x,
                                             @QueryParam("y") Long y,
                                             @QueryParam("creationDate") String creationDate,
                                             @QueryParam("enginePower") Integer enginePower,
                                             @QueryParam("numberOfWheels") Long numberOfWheels,
                                             @QueryParam("distanceTravelled") Double distanceTravelled,
                                             @QueryParam("type") VehicleType type,
                                             @QueryParam("sortBy") String sortBy,
                                             @QueryParam("order") String order,
                                             @QueryParam("page") Integer page,
                                             @QueryParam("limit") Integer limit) {
        VehicleFilterDTO filterDTO = VehicleFilterDTO.builder()
                .id(id)
                .name(name)
                .x(x)
                .y(y)
                .creationDate(creationDate)
                .enginePower(enginePower)
                .numberOfWheels(numberOfWheels)
                .distanceTravelled(distanceTravelled)
                .type(type)
                .sortBy(sortBy)
                .order(order)
                .page(page)
                .limit(limit)
                .build();
        return Response.ok(vehicleService.getAllVehiclesWithFilter(filterDTO)).build();
    }


    @GET
    @Path("/{id}")
    public Response getVehicleById(@PathParam("id") Long id) {
        return Response.ok(vehicleService.getVehicleById(id)).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteVehicleById(@PathParam("id") Long id) {
        return Response.ok(vehicleService.deleteVehicleById(id)).build();
    }

}

