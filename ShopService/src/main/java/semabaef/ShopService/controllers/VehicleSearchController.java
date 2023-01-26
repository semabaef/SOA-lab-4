//package semabaef.ShopService.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import semabaef.ShopService.models.document.Vehicle;
//import semabaef.ShopService.models.dto.vehicle.VehicleDTO;
//import semabaef.ShopService.models.enums.VehicleType;
//import semabaef.ShopService.services.VehicleService;
//import semabaef.ShopService.utils.validators.CommonValidator;
//
//
//import java.util.List;
//
//import static semabaef.ShopService.endpoints.VehicleEndpoints.*;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = MAIN_URL)
//public class VehicleSearchController {
//
//    private final VehicleService vehicleService;
//
//    private final CommonValidator validator;
//
//    @GetMapping(SEARCH_BY_TYPE)
//    public ResponseEntity<List<VehicleDTO>> searchVehiclesByType(@PathVariable("type") String typeString) {
//        VehicleType type = validator.validateVehicleType(typeString);
//        return new ResponseEntity<>(vehicleService.searchVehiclesByType(type), HttpStatus.OK);
//    }
//
//    @GetMapping(SEARCH_BY_ENGINE_POWER_IN_RANGE)
//    public ResponseEntity<List<VehicleDTO>> searchVehiclesByEnginePowerInRange(@PathVariable("from") String minS, @PathVariable("to") String maxS) {
//        Integer min = validator.validateInteger(minS);
//        Integer max = validator.validateInteger(maxS);
//        return new ResponseEntity<>(vehicleService.searchVehiclesByEnginePowerInRange(min, max), HttpStatus.OK);
//    }
//
//}
