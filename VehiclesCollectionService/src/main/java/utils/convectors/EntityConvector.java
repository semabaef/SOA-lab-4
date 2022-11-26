package utils.convectors;

import exceptions.ExceptionDescription;
import exceptions.HttpApplicationException;
import models.dto.coordinates.CoordinatesDTO;
import models.dto.vehicle.VehicleWithIdDTO;
import models.entities.Coordinates;
import models.entities.Vehicle;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class EntityConvector {

    public VehicleWithIdDTO convertVehicleEntityToDTO(Vehicle vehicle) {
        VehicleWithIdDTO vehicleWithIdDTO = new VehicleWithIdDTO();
        vehicleWithIdDTO.setId(vehicle.getId());
        vehicleWithIdDTO.setName(vehicle.getName());
        vehicleWithIdDTO.setCoordinates(convertCoordinatesToDTO(vehicle.getCoordinates()));
        vehicleWithIdDTO.setCreationDate(vehicle.getCreationDate().toString());
        vehicleWithIdDTO.setEnginePower(vehicle.getEnginePower());
        vehicleWithIdDTO.setNumberOfWheels(vehicle.getNumberOfWheels());
        vehicleWithIdDTO.setDistanceTravelled(vehicle.getDistanceTravelled());
        vehicleWithIdDTO.setType(vehicle.getType());
        return vehicleWithIdDTO;
    }

    public CoordinatesDTO convertCoordinatesToDTO(Coordinates coordinates) {
        CoordinatesDTO dto = new CoordinatesDTO();
        dto.setX(coordinates.getX());
        dto.setY(coordinates.getY());
        return dto;
    }


    public List<VehicleWithIdDTO> convertAndCheckListVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty())
            throw new HttpApplicationException(ExceptionDescription.OBJECT_VEHICLES_NOT_FOUND);
        return convertListVehicleToListDTO(vehicles);
    }

    public List<VehicleWithIdDTO> convertListVehicleToListDTO(List<Vehicle> vehicles) {
        return vehicles.stream().map((x) -> convertVehicleEntityToDTO(x)).collect(Collectors.toList());
    }
}
