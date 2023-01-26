package utils.convertors;

import models.dto.coordinates.CoordinatesDTO;
import models.dto.vehicle.VehicleNoIdDTO;
import models.dto.vehicle.VehicleWithIdDTO;
import models.entities.Coordinates;
import models.entities.Vehicle;


import javax.ejb.Stateless;
import java.time.LocalDateTime;

@Stateless
public class DtoConvertor {

    public Vehicle convertVehicleNoIdDtoToEntity(VehicleNoIdDTO vehicleNoIdDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleNoIdDTO.getName());
        vehicle.setCoordinates(convertCoordinatesDtoToEntity(vehicleNoIdDTO.getCoordinates()));
        vehicle.setCreationDay(getNowDay());
        vehicle.setCreationDate(LocalDateTime.now());
        vehicle.setEnginePower(vehicleNoIdDTO.getEnginePower());
        vehicle.setNumberOfWheels(vehicleNoIdDTO.getNumberOfWheels());
        vehicle.setDistanceTravelled(vehicleNoIdDTO.getDistanceTravelled());
        vehicle.setType(vehicleNoIdDTO.getType());
        return vehicle;
    }

    public Vehicle convertVehicleWithIdlDtoToEntity(VehicleWithIdDTO vehicleWithIdDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleWithIdDTO.getId());
        vehicle.setName(vehicleWithIdDTO.getName());
        vehicle.setCoordinates(convertCoordinatesDtoToEntity(vehicleWithIdDTO.getCoordinates()));
        vehicle.setCreationDay(parseDay(LocalDateTime.parse(vehicleWithIdDTO.getCreationDate())));
        vehicle.setCreationDate(LocalDateTime.parse(vehicleWithIdDTO.getCreationDate()));
        vehicle.setEnginePower(vehicleWithIdDTO.getEnginePower());
        vehicle.setNumberOfWheels(vehicleWithIdDTO.getNumberOfWheels());
        vehicle.setDistanceTravelled(vehicleWithIdDTO.getDistanceTravelled());
        vehicle.setType(vehicleWithIdDTO.getType());
        return vehicle;
    }

    public Coordinates convertCoordinatesDtoToEntity(CoordinatesDTO coordinatesDTO) {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesDTO.getX());
        coordinates.setY(coordinatesDTO.getY());
        return coordinates;
    }


    private String getNowDay(){
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        return String.format("%d-%02d-%02d", year,month,day);
    }

    private String parseDay(LocalDateTime localDateTime){
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        return String.format("%d-%02d-%02d", year,month,day);
    }
}
