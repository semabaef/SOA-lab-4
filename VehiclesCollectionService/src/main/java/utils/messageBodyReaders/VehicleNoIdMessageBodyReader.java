package utils.messageBodyReaders;

import exceptions.ExceptionDescription;
import exceptions.RestApplicationException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import models.dto.vehicle.VehicleNoIdDTO;

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
public class VehicleNoIdMessageBodyReader implements MessageBodyReader<VehicleNoIdDTO> {
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public VehicleNoIdDTO readFrom(Class<VehicleNoIdDTO> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        JAXBContext context;
        Unmarshaller toJava;
        VehicleNoIdDTO vehicle;
        try {
            context = JAXBContext.newInstance(VehicleNoIdDTO.class);
            toJava = context.createUnmarshaller();
            vehicle = (VehicleNoIdDTO) toJava.unmarshal(entityStream);
        } catch (Exception e) {
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        }
        return vehicle;
    }
}
