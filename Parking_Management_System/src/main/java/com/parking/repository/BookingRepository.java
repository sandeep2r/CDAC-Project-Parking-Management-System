package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.dto.BookingDto;
import com.parking.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query("select b from Booking b left join fetch b.agent left join fetch b.vehicle v left join fetch v.vehicleType left join fetch v.customer left join fetch b.payment left join fetch b.slot")
	List<Booking> getAllBookings();
	
	@Query("select b from Booking b left join fetch b.agent left join fetch b.vehicle v left join fetch v.vehicleType left join fetch v.customer left join fetch b.payment left join fetch b.slot where b.id = ?1")
	Booking getBookingById(Integer bookingId);
	
//	@Query("select new com.parking.dto.BookingDto(b.id as id, a.id as agentId, v.vehicleNumber as vehicleNumber, p.id as paymentId, s.slotId as slotId, b.status as status, b.checkInTime as checkInTime, b.checkOutTime as checkOutTime, b.isCheckedIn as isCheckedIn) from Booking b left join fetch b.agent a left join fetch b.vehicle v left join fetch b.payment p left join fetch b.slot s")
//	@Query("select "
//			+ "b.id as id,"
//			+ " a.id as agentId,"
//			+ " v.vehicleNumber as vehicleNumber,"
//			+ " p.id as paymentId,"
//			+ " s.slotId as slotId,"
//			+ " b.status as status,"
//			+ " b.checkInTime as checkInTime,"
//			+ " b.checkOutTime as checkOutTime,"
//			+ " b.isCheckedIn as isCheckedIn"
//			+ " from Booking b left join fetch b.agent a left join fetch b.vehicle v left join fetch v.vehicleType vt left join fetch v.customer c left join fetch b.payment p left join fetch b.slot s")
	
//	@Query("select b.id as id, a.id as agentId, v.vehicleNumber as vehicleNumber, p.id as paymentId, s.slotId as slotId, b.status as status, b.checkInTime as checkInTime, b.checkOutTime as checkOutTime, b.isCheckedIn as isCheckedIn, b from Booking b left join fetch b.agent a left join fetch b.vehicle v left join fetch v.vehicleType vt left join fetch v.customer c left join fetch b.payment p left join fetch b.slot s")
	
//	@Query("select "
//	+ "b.id as id,"
//	+ " a.id as agentId,"
//	+ " v.vehicleNumber as vehicleNumber,"
//	+ " p.id as paymentId,"
//	+ " s.slotId as slotId,"
//	+ " b.status as status,"
//	+ " b.checkInTime as checkInTime,"
//	+ " b.checkOutTime as checkOutTime,"
//	+ " b.isCheckedIn as isCheckedIn, b"
//	+ " from Booking b left join fetch b.agent a left join fetch b.vehicle v left join fetch v.vehicleType vt left join fetch v.customer c left join fetch b.payment p left join fetch b.slot s")

	
//	@Query("select new com.parking.dto.BookingDto(r.id as id,"
//			+ " r.id as agentId,"
//			+ " r.vehicleNumber as vehicleNumber,"
//			+ " r.id as paymentId,"
//			+ " r.slotId as slotId,"
//			+ " r.status as status,"
//			+ " r.checkInTime as checkInTime,"
//			+ " r.checkOutTime as checkOutTime,"
//			+ " r.isCheckedIn as isCheckedIn)"
//			+ " from "
//			+ "(select b.id as id, a.id as agentId, v.vehicleNumber as vehicleNumber, p.id as paymentId, s.slotId as slotId, b.status as status, b.checkInTime as checkInTime, b.checkOutTime as checkOutTime, b.isCheckedIn as isCheckedIn, b from Booking b left join fetch b.agent a left join fetch b.vehicle v left join fetch v.vehicleType vt left join fetch v.customer c left join fetch b.payment p left join fetch b.slot s) r")
	@Query("select b.id as id, a.id as agentId, v.vehicleNumber as vehicleNumber, p.id as paymentId, s.slotId as slotId, b.status as status, b.checkInTime as checkInTime, b.checkOutTime as checkOutTime, b.isCheckedIn as isCheckedIn, b from Booking b left join fetch b.agent a left join fetch b.vehicle v left join fetch v.vehicleType vt left join fetch v.customer c left join fetch b.payment p left join fetch b.slot s")
	List<BookingDto> getAllBookingDtos();
	
//	@Query("select new com.parking.dto.BookingDto(b.id as id, a.id as agentId, v.vehicleNumber as vehicleNumber, p.id as paymentId, s.slotId as slotId, b.status as status, b.checkInTime as checkInTime, b.checkOutTime as checkOutTime, b.isCheckedIn as isCheckedIn) from Booking b left join fetch b.agent a left join fetch b.vehicle v left join fetch v.vehicleType vt left join fetch v.customer left join fetch b.payment p left join fetch b.slot s where b.id = ?1")
//	BookingDto getBookingDtosById(Integer bookingId);
}