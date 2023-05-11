package com.parking.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entities.Admin;
import com.parking.entities.Agent;
import com.parking.entities.BookingStatus;
import com.parking.entities.SlotStatus;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    
	@Query("select c from Admin c where c.loginId = :loginId and c.password = :password")
	Admin loginValidation(String loginId, String password);
	
	@Query("SELECT SUM(p.totalAmount) FROM Booking b JOIN b.payment p WHERE b.checkInTime >= ?1 and b.checkInTime < ?2 and b.status != ?3")
	Integer getTodaysCollections(LocalDateTime fromDateTime, LocalDateTime toDateTime, BookingStatus status); 
	
	@Query("SELECT COUNT(DISTINCT b.slot) FROM Booking b WHERE b.checkInTime >= ?1 and b.checkInTime < ?2 and b.status != ?3")
	Integer getTodaysBookedSlots(LocalDateTime fromDateTime, LocalDateTime toDateTime, BookingStatus status);
	
	@Query("SELECT COUNT(s.slotId) FROM Slots s WHERE s.slotStatus = ?1")
	Integer getNoOfVehiclesParked(SlotStatus slotStatus);
	
	@Query("SELECT a FROM Agent a where a.loggedIn = ?1")
	Agent getCurrentlyWorkingAgent(Boolean loggedIn);
}