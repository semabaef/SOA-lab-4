package services;


import models.dto.vehicle.VehicleWithIdDTO;

import javax.ejb.Local;
import java.util.List;
@Local
public interface ShopVehiclesService {

    List<VehicleWithIdDTO> searchVehiclesByType(String type);

    List<VehicleWithIdDTO> searchVehiclesByEnginePowerInRange(Integer minEnginePower, Integer maxEnginePower);

}
