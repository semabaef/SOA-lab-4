package utils.parsers;

import exceptions.ParseXmlException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;
import models.dto.vehicle.VehicleNoIdDTO;
import models.dto.vehicle.VehicleWithIdDTO;

import javax.ejb.Stateless;
import java.io.StringReader;

@Stateless
public class XmlParser {


    @SneakyThrows
    public VehicleNoIdDTO parseXmlToVehicleNoId(String xmlString) {
        try {
            StringReader sr = new StringReader(xmlString);
            JAXBContext jaxbContext = JAXBContext.newInstance(VehicleNoIdDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (VehicleNoIdDTO) unmarshaller.unmarshal(sr);
        } catch (RuntimeException | JAXBException e) {
            throw new ParseXmlException();
        }
    }

    @SneakyThrows
    public VehicleWithIdDTO parseXmlToVehicleWithId(String xmlString) {
        try {
            StringReader sr = new StringReader(xmlString);
            JAXBContext jaxbContext = JAXBContext.newInstance(VehicleWithIdDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (VehicleWithIdDTO) unmarshaller.unmarshal(sr);
        } catch (RuntimeException | JAXBException e) {
            throw new ParseXmlException();
        }
    }
}
