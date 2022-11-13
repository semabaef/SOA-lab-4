package models.dto.common;

import lombok.Builder;
import lombok.Data;
import models.enums.VehicleType;

@Data
@Builder
public class VehicleFilterDTO {
    private Long id;
    private String name;
    private Float x;
    private Long y;
    private String creationDate;
    private Integer enginePower;
    private Long numberOfWheels;
    private Double distanceTravelled;
    private VehicleType type;
    private String sortBy;
    private String order;
    private Integer page;
    private Integer limit;
}
