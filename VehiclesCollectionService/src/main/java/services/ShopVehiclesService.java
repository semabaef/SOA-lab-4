package services;


import models.dto.vehicle.VehicleWithIdDTO;
import models.enums.VehicleType;

import javax.ejb.Local;
import java.util.List;
@Local
public interface ShopVehiclesService {

    List<VehicleWithIdDTO> searchVehiclesByType(VehicleType type);

    List<VehicleWithIdDTO> searchVehiclesByEnginePowerInRange(Integer minEnginePower, Integer maxEnginePower);

}
