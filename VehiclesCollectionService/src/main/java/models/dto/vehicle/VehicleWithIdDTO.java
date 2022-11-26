package models.dto.vehicle;

import exceptions.ExceptionDescription;
import exceptions.HttpApplicationException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.SneakyThrows;
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
    private String typeString; //Поле не может быть null

    /*
     * Валидация
     */
    private VehicleType type;

    public void validate() {
        if (this.id == null || this. id <=0 )
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);

        if (this.name == null || this.name.isEmpty())
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);

        if (this.coordinates == null)
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);

        if (this.enginePower <= 0)
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);

        if (this.numberOfWheels == null || this.numberOfWheels <= 0)
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);

        if (this.distanceTravelled <= 0)
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);

        if (this.typeString != null) {
            try {
                this.type = Enum.valueOf(VehicleType.class, this.typeString);
            } catch (Exception e) {
                throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
            }
        } else
            throw new HttpApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
    }

}
