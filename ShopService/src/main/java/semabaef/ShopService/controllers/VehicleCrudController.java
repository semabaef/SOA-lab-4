package semabaef.ShopService.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semabaef.ShopService.models.dto.vehicle.VehicleDTO;
import semabaef.ShopService.services.VehicleService;

import static semabaef.ShopService.endpoints.VehicleEndpoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = MAIN_URL)
public class VehicleCrudController {

    private final VehicleService vehicleService;

    @PostMapping(value = VEHICLES)
    public ResponseEntity<VehicleDTO> addNewVehicle(@RequestBody VehicleDTO vehicle) {
        vehicle.validate();
        return new ResponseEntity<>(vehicleService.addVehicle(vehicle), HttpStatus.CREATED);
    }

    @PutMapping(value = VEHICLES)
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicle) {
        vehicle.validate();
        return new ResponseEntity<>(vehicleService.updateVehicle(vehicle), HttpStatus.OK);
    }

    @DeleteMapping(VEHICLES_ID)
    public ResponseEntity<VehicleDTO> deleteVehicleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vehicleService.deleteVehicleById(id), HttpStatus.OK);

    }


}
