package semabaef.ShopService.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semabaef.ShopService.exceptions.ExceptionDescription;
import semabaef.ShopService.exceptions.RestApplicationException;
import semabaef.ShopService.models.document.Vehicle;
import semabaef.ShopService.models.dto.vehicle.VehicleDTO;
import semabaef.ShopService.models.enums.VehicleType;
import semabaef.ShopService.repositories.VehicleRepository;
import semabaef.ShopService.services.VehicleService;
import semabaef.ShopService.utils.convertors.DocumentConvertor;
import semabaef.ShopService.utils.convertors.DtoConvertor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final DtoConvertor dtoConvertor;

    private final DocumentConvertor documentConvertor;

    @Override
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.save(dtoConvertor.convertVehicleDtoToEntity(vehicleDTO));
        return documentConvertor.convertVehicleDocumentToDTO(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = findById(vehicleDTO.getId());
        vehicle = dtoConvertor.convertVehicleDtoToEntity(vehicleDTO);
        vehicleRepository.save(vehicle);
        return documentConvertor.convertVehicleDocumentToDTO(vehicle);
    }

    @Override
    public VehicleDTO deleteVehicleById(Long id) {
        Vehicle vehicle = findById(id);
        vehicleRepository.deleteById(id);
        return documentConvertor.convertVehicleDocumentToDTO(vehicle);
    }

    @Override
    public List<VehicleDTO> searchVehiclesByType(VehicleType type) {
        List<Vehicle> vehicles = vehicleRepository.findAllByVehicleType(type);
        return documentConvertor.convertListVehicleToListDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> searchVehiclesByEnginePowerInRange(Integer min, Integer max) {
        List<Vehicle> vehicles = vehicleRepository.findAllByEnginePowerIsBetween(min, max);
        return documentConvertor.convertListVehicleToListDTO(vehicles);
    }

    private Vehicle findById(Long id) {
        return vehicleRepository.findById(id).
                orElseThrow(() -> new RestApplicationException(ExceptionDescription.OBJECT_VEHICLE_NOT_FOUND));
    }
}
