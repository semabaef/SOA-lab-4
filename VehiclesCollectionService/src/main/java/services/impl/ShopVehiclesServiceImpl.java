package services.impl;

import dao.ShopDao;
import lombok.SneakyThrows;
import models.dto.vehicle.VehicleWithIdDTO;
import models.entities.Vehicle;
import models.enums.VehicleType;
import services.ShopVehiclesService;
import utils.convectors.EntityConvector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ShopVehiclesServiceImpl implements ShopVehiclesService {

    @EJB
    private EntityConvector entityConvector;

    @EJB
    private ShopDao shopDao;

    @Override
    public List<VehicleWithIdDTO> searchVehiclesByType(VehicleType type) {
        List<Vehicle> vehicles = shopDao.getAllVehiclesByType(type);
        return entityConvector.convertAndCheckListVehicles(vehicles);
    }

    @SneakyThrows
    @Override
    public List<VehicleWithIdDTO> searchVehiclesByEnginePowerInRange(Integer minEnginePower, Integer maxEnginePower) {
        List<Vehicle> vehicles = shopDao.getAllVehiclesByEnginePowerInRange(minEnginePower, maxEnginePower);
        return entityConvector.convertAndCheckListVehicles(vehicles);

    }

}
