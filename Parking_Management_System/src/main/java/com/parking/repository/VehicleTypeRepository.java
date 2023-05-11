package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parking.entities.SlotType;
import com.parking.entities.VehicleType;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, SlotType> {
}