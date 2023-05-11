package com.parking.services;

import java.util.List;

import com.parking.entities.VehicleType;


public interface VehicleTypeService {
    List<VehicleType> getAllVehicleTypes();
    VehicleType getVehicleTypeById(String id);
    VehicleType addVehicleType(VehicleType vehicleType);
    VehicleType updateVehicleType(String id, VehicleType vehicleType);
    void deleteVehicleType(String id);
}