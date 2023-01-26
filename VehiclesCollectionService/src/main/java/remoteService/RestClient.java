package remoteService;


import exceptions.ExceptionDescription;
import exceptions.RestApplicationException;
import models.dto.vehicle.VehicleWithIdDTO;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static remoteService.ShopServiceEndpoints.*;

@Stateless
public class RestClient {


    private final Client client = ClientBuilder.newClient();


    public Response sendNewVehicleToElasticsearchService(VehicleWithIdDTO vehicleDTO) {
        Response response;
        try {
            response = client.target(BASE_URL).path(VEHICLES)
                    .request(MediaType.APPLICATION_XML)
                    .post(Entity.xml(writeVehicleDtoToXML(vehicleDTO)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApplicationException(ExceptionDescription.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public Response sendUpdatingVehicleToElasticsearchService(VehicleWithIdDTO vehicleDTO) {
        Response response;
        try {
            response = client.target(BASE_URL).path(VEHICLES)
                    .request(MediaType.APPLICATION_XML)
                    .put(Entity.xml(writeVehicleDtoToXML(vehicleDTO)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApplicationException(ExceptionDescription.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public Response sendRemovalRequestVehicleToElasticsearchService(Long id) {
        Response response;
        try {
            response = client.target(BASE_URL).path(VEHICLES_ID)
                    .resolveTemplate("id", id)
                    .request(MediaType.APPLICATION_XML)
                    .delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApplicationException(ExceptionDescription.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    private String writeVehicleDtoToXML(VehicleWithIdDTO vehicle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<Vehicle>");
        stringBuilder.append("<id>").append(vehicle.getId()).append("</id>");
        stringBuilder.append("<name>").append(vehicle.getName()).append("</name>");
        stringBuilder.append("<creationDate>").append(vehicle.getCreationDate()).append("</creationDate>");
        stringBuilder.append("<coordinates>");
        stringBuilder.append("<x>").append(vehicle.getCoordinates().getX()).append("</x>");
        stringBuilder.append("<y>").append(vehicle.getCoordinates().getY()).append("</y>");
        stringBuilder.append("</coordinates>");
        stringBuilder.append("<enginePower>").append(vehicle.getEnginePower()).append("</enginePower>");
        stringBuilder.append("<numberOfWheels>").append(vehicle.getNumberOfWheels()).append("</numberOfWheels>");
        stringBuilder.append("<distanceTravelled>").append(vehicle.getDistanceTravelled()).append("</distanceTravelled>");
        stringBuilder.append("<type>").append(vehicle.getType()).append("</type>");
        stringBuilder.append("</Vehicle>");
        return stringBuilder.toString();
    }

}
