package com.parking.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.VehicleDto;
import com.parking.entities.Vehicle;
import com.parking.services.VehicleService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/listDto")
    public ResponseEntity<List<VehicleDto>> getAllVehicleDtos() {
        List<VehicleDto> vehicleDtos = vehicleService.getAllVehicleDtos();
        return new ResponseEntity<>(vehicleDtos, HttpStatus.OK);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/Dto/{vehicleNumber}")
    public ResponseEntity<VehicleDto> getVehicleDtoByNumber(@PathVariable("vehicleNumber") String vehicleNumber) {
        VehicleDto vehicleDto = vehicleService.getVehicleDtoByNumber(vehicleNumber);
        if (vehicleDto != null) {
            return new ResponseEntity<>(vehicleDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{vehicleNumber}")
    public ResponseEntity<Vehicle> getVehicleByNumber(@PathVariable("vehicleNumber") String vehicleNumber) {
        Vehicle vehicle = vehicleService.getVehicleByNumber(vehicleNumber);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("customer/Dto/{customerid}")
    public ResponseEntity<List<VehicleDto>> getVehicleDtoByCustomer(@PathVariable("customerid") Integer customerid) {
        List<VehicleDto> allVehicleDto = vehicleService.getVehicleDtoByCustomer(customerid);
        if (allVehicleDto == null || allVehicleDto.size() <= 0) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allVehicleDto, HttpStatus.OK);
        }
    }
    
    @GetMapping("customer/{customerid}")
    public ResponseEntity<List<Vehicle>> getVehicleByCustomer(@PathVariable("customerid") Integer customerid) {
        List<Vehicle> allVehicle = vehicleService.getVehicleByCustomer(customerid);
        if (allVehicle == null || allVehicle.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        	return new ResponseEntity<>(allVehicle, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicle> createVehicleByDto(@Valid @RequestBody VehicleDto vehicleDto) {
        Vehicle createdVehicle = vehicleService.createVehicleByDto(vehicleDto);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleNumber}")
    public ResponseEntity<Vehicle> updateVehicleByDto(@PathVariable("vehicleNumber") String vehicleNumber, @Valid @RequestBody VehicleDto vehicleDto) {
        Vehicle updatedVehicle = vehicleService.updateVehicleByDto(vehicleNumber, vehicleDto);
        if (updatedVehicle != null) {
            return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{vehicleNumber}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleNumber") String vehicleNumber) {
        vehicleService.deleteVehicle(vehicleNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   
}
