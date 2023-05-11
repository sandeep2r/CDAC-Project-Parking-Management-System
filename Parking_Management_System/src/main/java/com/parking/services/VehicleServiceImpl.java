package com.parking.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.VehicleDto;
import com.parking.entities.Customer;
import com.parking.entities.Vehicle;
import com.parking.entities.VehicleType;
import com.parking.repository.VehicleRepository;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private VehicleTypeService vehicleTypeService;

	@Autowired
	private CustomerService customerService;

	@Override
	public List<VehicleDto> getAllVehicleDtos() {
		return vehicleRepository.getAllVehicleDto();
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAllVehicles();
	}

	@Override
	public VehicleDto getVehicleDtoByNumber(String vehicleNumber) {
		return vehicleRepository.findVehicleDtoById(vehicleNumber);
	}

	@Override
	public Vehicle getVehicleByNumber(String vehicleNumber) {
		Vehicle vehicle = vehicleRepository.findVehicleById(vehicleNumber);
		return vehicle;
	}
	

	@Override
	public List<VehicleDto> getVehicleDtoByCustomer(Integer customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		if(customer != null)
			return vehicleRepository.findVehicleDtoByCustomer(customer);
		else
			return null;
	}

	@Override
	public List<Vehicle> getVehicleByCustomer(Integer customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		if(customer != null)
			return vehicleRepository.findVehicleByCustomer(customer);
		else
			return null;
	}

	@Override
	public Vehicle createVehicleByDto(VehicleDto vehicleDto) {
		VehicleType vt = vehicleDto.getVehicleType() != null
				? vehicleTypeService.getVehicleTypeById(vehicleDto.getVehicleType().toString())
				: null;
		Customer ct = vehicleDto.getCustomer() != null ? customerService.getCustomerById(vehicleDto.getCustomer())
				: null;
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleNumber(vehicleDto.getVehicleNumber());
		vehicle.setModelName(vehicleDto.getModelName());
		vehicle.setCustomer(ct);
		vehicle.setVehicleType(vt);
		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle updateVehicleByDto(String vehicleNumber, VehicleDto vehicleDto) {

		Vehicle vehicle = vehicleRepository.findVehicleById(vehicleNumber);
		if (vehicle != null) {
			Customer ct = vehicleDto.getCustomer() != null ? customerService.getCustomerById(vehicleDto.getCustomer())
					: null;

			VehicleType vt = vehicleDto.getVehicleType() != null
					? vehicleTypeService.getVehicleTypeById(vehicleDto.getVehicleType().toString())
					: null;

			vehicle.setVehicleType(vt);
			vehicle.setCustomer(ct);
			vehicle.setModelName(vehicleDto.getModelName());

			return vehicleRepository.save(vehicle);
		} else
			return null;

	}

	@Override
	public void deleteVehicle(String vehicleNumber) {
		vehicleRepository.deleteById(vehicleNumber);

	}

}
