package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.dto.VehicleDto;
import com.parking.entities.Customer;
import com.parking.entities.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
	@Query("select v from Vehicle v left join fetch v.customer left join fetch v.vehicleType")
	List<Vehicle> findAllVehicles();
	
	@Query("select v from Vehicle v left join fetch v.customer left join fetch v.vehicleType where v.vehicleNumber=?1")
	//@Query("select v from Vehicle v left join fetch v.customer left join fetch v.vehicleType")
	Vehicle findVehicleById(String vehicleNumber);
	
	@Query("select new com.parking.dto.VehicleDto(v.vehicleNumber as vehicleNumber, t.vehicleType as vehicleType, c.id as customer, v.modelName as modelName) from Vehicle v left join v.vehicleType t left join v.customer c")
	List<VehicleDto> getAllVehicleDto();
	
	@Query("select new com.parking.dto.VehicleDto(v.vehicleNumber as vehicleNumber, t.vehicleType as vehicleType, c.id as customer, v.modelName as modelName) from Vehicle v left join v.vehicleType t left join v.customer c where v.vehicleNumber=?1")
	VehicleDto findVehicleDtoById(String vehicleNumber);
	
	@Query("select v from Vehicle v left join fetch v.customer left join fetch v.vehicleType where v.customer=?1")
	List<Vehicle> findVehicleByCustomer(Customer customerId);
	
	@Query("select new com.parking.dto.VehicleDto(v.vehicleNumber as vehicleNumber, t.vehicleType as vehicleType, c.id as customer, v.modelName as modelName) from Vehicle v left join v.vehicleType t left join v.customer c where v.customer=?1")
	List<VehicleDto> findVehicleDtoByCustomer(Customer customerId);
	
}

