package utils.messageBodyWriters.vehicle;

import models.dto.vehicle.VehicleWithIdDTO;

import javax.ejb.Stateless;
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

@Provider
@Produces(MediaType.APPLICATION_XML)
public class VehicleMessageBodyWriter implements MessageBodyWriter<VehicleWithIdDTO> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type == VehicleWithIdDTO.class;
    }

    @Override
    public long getSize(VehicleWithIdDTO vehicleWithIdDTO, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return MessageBodyWriter.super.getSize(vehicleWithIdDTO, type, genericType, annotations, mediaType);
    }

    @Override
    public void writeTo(VehicleWithIdDTO vehicle, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        Writer writer = new PrintWriter(outputStream);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
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
        writer.flush();
        writer.close();
    }
}
