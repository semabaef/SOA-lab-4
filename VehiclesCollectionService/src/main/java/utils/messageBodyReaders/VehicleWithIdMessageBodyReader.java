package utils.messageBodyReaders;

import exceptions.ExceptionDescription;
import exceptions.RestApplicationException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import models.dto.vehicle.VehicleWithIdDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes(MediaType.APPLICATION_XML)
public class VehicleWithIdMessageBodyReader implements MessageBodyReader<VehicleWithIdDTO> {
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public VehicleWithIdDTO readFrom(Class<VehicleWithIdDTO> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        JAXBContext context;
        Unmarshaller toJava;
        VehicleWithIdDTO vehicle;
        try {
            context = JAXBContext.newInstance(VehicleWithIdDTO.class);
            toJava = context.createUnmarshaller();
            vehicle = (VehicleWithIdDTO) toJava.unmarshal(entityStream);
        } catch (Exception e) {
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        }
        return vehicle;
    }
}
