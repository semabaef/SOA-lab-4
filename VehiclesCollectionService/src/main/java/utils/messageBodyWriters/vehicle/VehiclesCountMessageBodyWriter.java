package utils.messageBodyWriters.vehicle;

import models.dto.common.VehiclesCountDTO;

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
public class VehiclesCountMessageBodyWriter implements MessageBodyWriter<VehiclesCountDTO> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(VehiclesCountDTO vehiclesCountDTO, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        Writer writer = new PrintWriter(entityStream);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.write("<VehiclesCount>" + vehiclesCountDTO.getCount() + "</VehiclesCount>");
        writer.flush();
        writer.close();
    }
}

