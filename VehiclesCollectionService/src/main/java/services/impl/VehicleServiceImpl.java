package services.impl;

import dao.CoordinatesDao;
import dao.VehicleCrudDao;
import dao.VehicleExtraOperationsDao;;
import exceptions.ExceptionDescription;
import exceptions.HttpApplicationException;
import lombok.SneakyThrows;
import models.dto.common.VehicleFilterDTO;
import models.dto.vehicle.VehicleNoIdDTO;
import models.dto.vehicle.VehicleWithIdDTO;
import models.entities.Coordinates;
import models.entities.Vehicle;
import models.enums.SortByType;
import models.enums.SortOrder;
import services.CoordinatesService;
import services.VehicleService;
import utils.convectors.DtoConvector;
import utils.convectors.EntityConvector;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.HashMap;
import java.util.List;

@Stateless
public class VehicleServiceImpl implements VehicleService {

    @EJB
    private CoordinatesService coordinatesService;

    @EJB
    private VehicleCrudDao vehicleCrudDao;

    @EJB
    private CoordinatesDao coordinatesDao;

    @EJB
    private VehicleExtraOperationsDao vehicleExtraOperationsDao;

    @EJB
    private DtoConvector dtoConvector;

    @EJB
    private EntityConvector entityConvector;

    @Override
    public VehicleWithIdDTO addNewVehicle(VehicleNoIdDTO vehicleNoIdDTO) {
        Vehicle vehicle = dtoConvector.convertVehicleNoIdDtoToEntity(vehicleNoIdDTO);
        Coordinates coordinates = coordinatesService.checkAndSaveCoordinatesIfAreNotInBD(vehicle.getCoordinates());
        vehicle.setCoordinates(coordinates);
        vehicleCrudDao.save(vehicle);
        return entityConvector.convertVehicleEntityToDTO(vehicle);
    }

    @SneakyThrows
    @Override
    public VehicleWithIdDTO updateVehicle(VehicleWithIdDTO vehicleWithIdDTO) {
        Vehicle vehicle = findAndCheckVehicleById(vehicleWithIdDTO.getId());
        vehicle = dtoConvector.convertVehicleWithIdlDtoToEntity(vehicleWithIdDTO);
        Coordinates coordinates = coordinatesService.checkAndSaveCoordinatesIfAreNotInBD(vehicle.getCoordinates());
        vehicle.setCoordinates(coordinates);
        vehicleCrudDao.update(vehicle);
        return vehicleWithIdDTO;
    }

    @SneakyThrows
    @Override
    public VehicleWithIdDTO getVehicleById(Long id) {
        Vehicle vehicle = findAndCheckVehicleById(id);
        return entityConvector.convertVehicleEntityToDTO(vehicle);
    }

    @SneakyThrows
    @Override
    public VehicleWithIdDTO deleteVehicleById(Long id) {
        Vehicle vehicle = findAndCheckVehicleById(id);
        vehicleCrudDao.delete(vehicle);
        return entityConvector.convertVehicleEntityToDTO(vehicle);
    }


    @SneakyThrows
    @Override
    public List<VehicleWithIdDTO> getAllVehiclesWithFilter(VehicleFilterDTO filterDTO) {
        List<Vehicle> vehicles;
        HashMap<String, String> fieldToFilter = new HashMap<>();
        if (filterDTO.getId() != null)
            fieldToFilter.put("id", filterDTO.getId().toString());
        if (filterDTO.getName() != null)
            fieldToFilter.put("name", filterDTO.getName());
        if (filterDTO.getCreationDate() != null)
            fieldToFilter.put("creationDate", filterDTO.getCreationDate());
        if (filterDTO.getX() != null)
            fieldToFilter.put("x", filterDTO.getX().toString());
        if (filterDTO.getY() != null)
            fieldToFilter.put("y", filterDTO.getY().toString());
        if (filterDTO.getEnginePower() != null)
            fieldToFilter.put("enginePower", filterDTO.getEnginePower().toString());
        if (filterDTO.getNumberOfWheels() != null)
            fieldToFilter.put("numberOfWheels", filterDTO.getNumberOfWheels().toString());
        if (filterDTO.getDistanceTravelled() != null)
            fieldToFilter.put("distanceTravelled", filterDTO.getDistanceTravelled().toString());
        if (filterDTO.getType() != null)
            fieldToFilter.put("type", filterDTO.getType().toString());

        if (filterDTO.getSortBy() != null)
            //ищем с фильтром и сортируем
            vehicles = vehicleCrudDao.getAllMatchingFieldsSortedPageable(
                    fieldToFilter,
                    SortByType.valueOf(filterDTO.getSortBy()),
                    SortOrder.valueOf(filterDTO.getOrder()),
                    filterDTO.getPage(),
                    filterDTO.getLimit());
        else
            //ищем только с фильтром без сортировки
            vehicles = vehicleCrudDao.getAllMatchingFieldsPageable(
                    fieldToFilter,
                    filterDTO.getPage(),
                    filterDTO.getLimit());
        return entityConvector.convertAndCheckListVehicles(vehicles);
    }

    @Override
    public Long countVehiclesWhereTypeIs(String type) {
        return vehicleExtraOperationsDao.countVehiclesWhereTypeIs(type);
    }

    @SneakyThrows
    @Override
    public List<VehicleWithIdDTO> getVehiclesWhereNameLike(String name) {
        List<Vehicle> vehicles = vehicleExtraOperationsDao.getVehiclesWhereNameLike(name);
        return entityConvector.convertAndCheckListVehicles(vehicles);
    }

    @SneakyThrows
    @Override
    public List<VehicleWithIdDTO> getVehiclesWhereEnginePowerIsLessThan(Integer enginePower) {
        List<Vehicle> vehicles = vehicleExtraOperationsDao.getVehiclesWhereEnginePowerIsLessThan(enginePower);
        return entityConvector.convertAndCheckListVehicles(vehicles);
    }

    private Vehicle findAndCheckVehicleById(Long id) {
        Vehicle vehicle = vehicleCrudDao.findById(id);
        if (vehicle == null)
            throw new HttpApplicationException(ExceptionDescription.OBJECT_VEHICLE_NOT_FOUND);
        return vehicle;
    }

}
