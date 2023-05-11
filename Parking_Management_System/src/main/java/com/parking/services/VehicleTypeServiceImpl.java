package com.parking.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.entities.SlotType;
import com.parking.entities.VehicleType;
import com.parking.repository.VehicleTypeRepository;

@Service
@Transactional
public class VehicleTypeServiceImpl implements VehicleTypeService {

	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;

	@Override
	public List<VehicleType> getAllVehicleTypes() {
		return vehicleTypeRepository.findAll();
	}

	@Override
	public VehicleType getVehicleTypeById(String id) {
		return vehicleTypeRepository.findById(SlotType.valueOf(id)).orElse(null);
	}

	@Override
	public VehicleType addVehicleType(VehicleType vehicleType) {
		return vehicleTypeRepository.save(vehicleType);
	}

	@Override
	public VehicleType updateVehicleType(String id, VehicleType vehicleType) {
		VehicleType vehicle = vehicleTypeRepository.findById(SlotType.valueOf(id)).orElse(null);
		if (vehicle != null) {

			vehicle.setRate(vehicleType.getRate());
			return vehicleTypeRepository.save(vehicle);
		} else {
			return null;
		}
	}

	@Override
	public void deleteVehicleType(String id) {
		vehicleTypeRepository.deleteById(SlotType.valueOf(id));
	}
}