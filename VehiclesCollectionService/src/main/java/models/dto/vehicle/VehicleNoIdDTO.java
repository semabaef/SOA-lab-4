package models.dto.vehicle;


import exceptions.ExceptionDescription;
import exceptions.RestApplicationException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import models.dto.coordinates.CoordinatesDTO;
import models.enums.VehicleType;


@Data
@Getter
@Setter
@XmlRootElement(name = "Vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleNoIdDTO {
    @XmlElement(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой
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
        boolean fail = false;

        if (this.name == null || this.name.isEmpty())
            fail = true;

        if (this.coordinates == null || this.coordinates.getX() == null || this.coordinates.getY() == null)
            fail = true;

        if (this.enginePower <= 0)
            fail = true;

        if (this.numberOfWheels == null || this.numberOfWheels <= 0)
            fail = true;

        if (this.distanceTravelled == null || this.distanceTravelled <= 0)
            fail = true;

        if (this.typeString != null) {
            try {
                this.type = Enum.valueOf(VehicleType.class, this.typeString);
            } catch (Exception e) {
                fail = true;
            }
        } else
            fail = true;

        if (fail)
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);

    }

}

