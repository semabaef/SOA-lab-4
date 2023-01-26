package semabaef.ShopService.services;

import semabaef.ShopService.models.dto.vehicle.VehicleDTO;
import semabaef.ShopService.models.enums.VehicleType;

import java.util.List;

public interface VehicleService {

    VehicleDTO addVehicle(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);

    VehicleDTO deleteVehicleById(Long id);

    List<semabaef.shop.search.VehicleDTO> searchVehiclesByType(VehicleType type);

    List<semabaef.shop.search.VehicleDTO> searchVehiclesByEnginePowerInRange(Integer min, Integer max);
}
