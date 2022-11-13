package models.dto.vehicle;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import models.dto.coordinates.CoordinatesDTO;
import models.enums.VehicleType;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement(name = "Vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleWithIdDTO {
    @XmlElement(name = "id")
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement(name = "creationDate")
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlElement(name = "coordinates")
    private CoordinatesDTO coordinates; //Поле не может быть null
    @XmlElement(name = "enginePower")
    private int enginePower; //Значение поля должно быть больше 0
    @XmlElement(name = "numberOfWheels")
    private Long numberOfWheels; //Поле не может быть null, Значение поля должно быть больше 0
    @XmlElement(name = "distanceTravelled")
    private Double distanceTravelled; //Поле может быть null, Значение поля должно быть больше 0
    @XmlElement(name = "type")
    private VehicleType type; //Поле не может быть null
}
