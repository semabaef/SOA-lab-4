package semabaef.ShopService.models.dto.vehicle;


import lombok.Data;
import semabaef.ShopService.exceptions.ExceptionDescription;
import semabaef.ShopService.exceptions.RestApplicationException;
import semabaef.ShopService.models.dto.coordinates.CoordinatesDTO;
import semabaef.ShopService.models.enums.VehicleType;


@Data
public class VehicleDTO {

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой

    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private CoordinatesDTO coordinates; //Поле не может быть null
    private int enginePower; //Значение поля должно быть больше 0
    private Long numberOfWheels; //Поле не может быть null, Значение поля должно быть больше 0
    private Double distanceTravelled; //Поле может быть null, Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null

    /*
     * Валидация
     */
    public void validate() {
        boolean fail = false;

        if (this.id == null || this.id <= 0)
            fail = true;

        if (this.name == null || this.name.isEmpty())
            fail = true;

        if (this.coordinates == null)
            fail = true;

        if (this.enginePower <= 0)
            fail = true;

        if (this.numberOfWheels == null || this.numberOfWheels <= 0)
            fail = true;

        if (this.distanceTravelled <= 0)
            fail = true;

        if (this.type == null)
            fail = true;

        if (fail)
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
    }

}
