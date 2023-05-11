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

import com.parking.entities.VehicleType;
import com.parking.services.VehicleTypeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/vehicletype")
public class VehicleTypeController {

	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@GetMapping("/list")
	public ResponseEntity<List<VehicleType>> getAllVehicleTypes() {
		List<VehicleType> vehicleTypeSlot= vehicleTypeService.getAllVehicleTypes();
		return new ResponseEntity<>(vehicleTypeSlot,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VehicleType> getVehicleTypeById(@PathVariable("id") String id) {
		VehicleType vehicleType= vehicleTypeService.getVehicleTypeById(id);
		
		 if (vehicleType != null) {
	            return new ResponseEntity<>(vehicleType, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		
	}
	
	@PostMapping("/add") //Normally this functionality is not need
	public ResponseEntity<VehicleType> addVehicleType(@Valid @RequestBody VehicleType vehicleType) {
		VehicleType Createvehicle = vehicleTypeService.addVehicleType(vehicleType);
		return new ResponseEntity<>(Createvehicle, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VehicleType> updateVehicleType(@PathVariable("id") String id, @Valid @RequestBody VehicleType vehicleType) {
		VehicleType updateVehicle= vehicleTypeService.updateVehicleType(id, vehicleType);
		
		if(updateVehicle != null) {
			return new ResponseEntity<>(updateVehicle, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		
	}

	@DeleteMapping("/{id}") //Normally this functionality is not need
	public ResponseEntity<Void> deleteVehicleType(@PathVariable("id") String id) {
		vehicleTypeService.deleteVehicleType(id);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
