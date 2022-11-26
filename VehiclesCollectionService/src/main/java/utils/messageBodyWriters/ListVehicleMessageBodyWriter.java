package utils.messageBodyWriters;

import models.dto.vehicle.VehicleWithIdDTO;

import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class ListVehicleMessageBodyWriter implements MessageBodyWriter<List<VehicleWithIdDTO>> {


    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override

    public void writeTo(List<VehicleWithIdDTO> vehicleWithIdDTOS, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        Writer writer = new PrintWriter(outputStream);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.write("<VehiclesList>");
        vehicleWithIdDTOS.stream().forEach((vehicle) -> {
                    try {
                        writer.write("<Vehicle>");
                        writer.write("<id>" + vehicle.getId() + "</id>");
                        writer.write("<name>" + vehicle.getName() + "</name>");
                        writer.write("<creationDate>" + vehicle.getCreationDate() + "</creationDate>");
                        writer.write("<coordinates>");
                        writer.write("<x>" + vehicle.getCoordinates().getX() + "</x>");
                        writer.write("<y>" + vehicle.getCoordinates().getY() + "</y>");
                        writer.write("</coordinates>");
                        writer.write("<enginePower>" + vehicle.getEnginePower() + "</enginePower>");
                        writer.write("<numberOfWheels>" + vehicle.getNumberOfWheels() + "</numberOfWheels>");
                        writer.write("<distanceTravelled>" + vehicle.getDistanceTravelled() + "</distanceTravelled>");
                        writer.write("<type>" + vehicle.getType() + "</type>");
                        writer.write("</Vehicle>");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        writer.write("</VehiclesList>");
        writer.flush();
        writer.close();
    }

}

