package com.parking.services;

import java.util.List;

import com.parking.dto.VehicleDto;
import com.parking.entities.Vehicle;

public interface VehicleService {

	// GET

	List<VehicleDto> getAllVehicleDtos();

	List<Vehicle> getAllVehicles();

	VehicleDto getVehicleDtoByNumber(String vehicleNumber);

	Vehicle getVehicleByNumber(String vehicleNumber);
	
	List<VehicleDto> getVehicleDtoByCustomer(Integer customerId);
	
	List<Vehicle> getVehicleByCustomer(Integer customerId);

	// POST

	Vehicle createVehicleByDto(VehicleDto vehicleDto);

	// PUT

	Vehicle updateVehicleByDto(String vehicleNumber, VehicleDto vehicleDto);

	// DELETE

	void deleteVehicle(String vehicleNumber);
}
