package semabaef.ShopService.utils.convertors;


import org.springframework.stereotype.Component;
import semabaef.ShopService.exceptions.ExceptionDescription;
import semabaef.ShopService.exceptions.RestApplicationException;
import semabaef.ShopService.models.document.Vehicle;
import semabaef.ShopService.models.dto.coordinates.CoordinatesDTO;
import semabaef.ShopService.models.dto.vehicle.VehicleDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentConvertor {

    public VehicleDTO convertVehicleDocumentToDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(Long.valueOf(vehicle.getId()));
        vehicleDTO.setName(vehicle.getName());
        vehicleDTO.setCoordinates(convertCoordinatesToDTO(vehicle.getX(), vehicle.getY()));
        vehicleDTO.setCreationDate(vehicle.getCreationDate().toString());
        vehicleDTO.setEnginePower(vehicle.getEnginePower());
        vehicleDTO.setNumberOfWheels(vehicle.getNumberOfWheels());
        vehicleDTO.setDistanceTravelled(vehicle.getDistanceTravelled());
        vehicleDTO.setType(vehicle.getType());
        return vehicleDTO;
    }

    public CoordinatesDTO convertCoordinatesToDTO(Float x, Long y) {
        CoordinatesDTO dto = new CoordinatesDTO();
        dto.setX(x);
        dto.setY(y);
        return dto;
    }


    public List<VehicleDTO> convertAndCheckListVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty())
            throw new RestApplicationException(ExceptionDescription.OBJECT_VEHICLES_NOT_FOUND);
        return convertListVehicleToListDTO(vehicles);

    }

    public List<VehicleDTO> convertListVehicleToListDTO(List<Vehicle> vehicles) {
        return vehicles.stream().map(this::convertVehicleDocumentToDTO).collect(Collectors.toList());
    }

    public List<semabaef.shop.search.VehicleDTO> convertListVehicleToListSoapDTO(List<Vehicle> vehicles) {
        return vehicles.stream().map(this::convertVehicleDocumentToSoapDTO).collect(Collectors.toList());
    }

    public semabaef.shop.search.VehicleDTO convertVehicleDocumentToSoapDTO(Vehicle vehicle) {
        semabaef.shop.search.VehicleDTO vehicleDTO = new semabaef.shop.search.VehicleDTO();
        vehicleDTO.setId(Long.valueOf(vehicle.getId()));
        vehicleDTO.setName(vehicle.getName());
        semabaef.shop.search.CoordinatesDTO coordinatesDTO = new semabaef.shop.search.CoordinatesDTO();
        coordinatesDTO.setX(vehicle.getX());
        coordinatesDTO.setY(vehicle.getY());
        vehicleDTO.setCoordinates(coordinatesDTO);
        vehicleDTO.setCreationDate(vehicle.getCreationDate());
        vehicleDTO.setEnginePower(vehicle.getEnginePower());
        vehicleDTO.setNumberOfWheels(vehicle.getNumberOfWheels());
        vehicleDTO.setDistanceTravelled(vehicle.getDistanceTravelled());
        vehicleDTO.setType(vehicle.getType().name());
        return vehicleDTO;
    }
}
