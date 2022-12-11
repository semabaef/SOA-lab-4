package semabaef.ShopService.utils.convertors;


import org.springframework.stereotype.Component;
import semabaef.ShopService.models.document.Vehicle;
import semabaef.ShopService.models.dto.vehicle.VehicleDTO;

import java.time.LocalDateTime;

@Component
public class DtoConvertor {

    public Vehicle convertVehicleDtoToEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId().toString());
        vehicle.setName(vehicleDTO.getName());
        vehicle.setX(vehicleDTO.getCoordinates().getX());
        vehicle.setY(vehicleDTO.getCoordinates().getY());
        vehicle.setCreationDate(vehicleDTO.getCreationDate());
        vehicle.setEnginePower(vehicleDTO.getEnginePower());
        vehicle.setNumberOfWheels(vehicleDTO.getNumberOfWheels());
        vehicle.setDistanceTravelled(vehicleDTO.getDistanceTravelled());
        vehicle.setType(vehicleDTO.getType());
        return vehicle;
    }

}
