package com.parking.services;

import java.util.List;

import com.parking.dto.BookingDto;
import com.parking.entities.Booking;

public interface BookingService {

	//GET
	List<Booking> getAllBookings();

	Booking getBookingById(Integer bookingId);

	List<BookingDto> getAllBookingDtos();

//	BookingDto getBookingDtoById(Integer bookingId);
	
	//POST
	Booking createBooking(BookingDto bookingDto);

	//PUT
	Booking updateBooking(Integer bookingId, BookingDto bookingDto);

	//DELETE
	void deleteBooking(int bookingId);
}
