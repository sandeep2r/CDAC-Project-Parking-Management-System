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

import com.parking.dto.BookingDto;
import com.parking.dto.BookingDtoForAgent;
import com.parking.dto.VehicleDto;
import com.parking.entities.Booking;
import com.parking.entities.SlotStatus;
import com.parking.entities.Slots;
import com.parking.services.BookingService;
import com.parking.services.SlotsService;
import com.parking.services.VehicleService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private SlotsService slotsService;
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/listDto")
    public ResponseEntity<List<BookingDto>> getAllBookingDtos() {
        List<BookingDto> bookingDtos = bookingService.getAllBookingDtos();
        return new ResponseEntity<>(bookingDtos, HttpStatus.OK);
    }

//    @GetMapping("/Dto/{bookingId}")
//    public ResponseEntity<BookingDto> getBookingDtoById(@PathVariable("bookingId") int bookingId) {
//        BookingDto bookingDto = bookingService.getBookingDtoById(bookingId);
//        if (bookingDto != null) {
//            return new ResponseEntity<>(bookingDto, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingDto bookingDto) {
    	//Finding slot and marking for booking if available
    	Slots slotIdAndPreviousStatusObject = slotsService.availableSlotMarkedForBooking(bookingDto.getCheckInTime(),
				bookingDto.getCheckOutTime(), bookingDto.getVehicleNumber());
    	
    	//if slot is not available then return No_CONTENT
    	if(slotIdAndPreviousStatusObject == null)
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	
    	String slotId = slotIdAndPreviousStatusObject.getSlotId();
    	SlotStatus previousStatus = slotIdAndPreviousStatusObject.getSlotStatus();
    	
    	bookingDto.setSlotId(slotId);
    	//Making a booking entry for marked slot
        Booking createdBooking = bookingService.createBooking(bookingDto);
        Slots slot = slotsService.getSlotById(slotId);
        if(createdBooking != null) {
        	
        	if(previousStatus.equals(SlotStatus.FILLED))
        		slot.setSlotStatus(SlotStatus.FILLED);
        	else
        		slot.setSlotStatus(SlotStatus.BOOKED);
        	
        	slotsService.updateSlot(slotId, slot);
        	return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        }
        else {
        	slot.setSlotStatus(previousStatus);
        	slotsService.updateSlot(slotId, slot);
        	return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    
    @PostMapping("/addbyagent")
    public ResponseEntity<Booking> createBookingByAgent(@Valid @RequestBody BookingDtoForAgent bookingDtoForAgent) {
    	
    	VehicleDto vehicleDto = new VehicleDto();
    	vehicleDto.setVehicleNumber(bookingDtoForAgent.getVehicleNumber());
    	vehicleDto.setModelName("NOT_GIVEN");
    		Slots slot = slotsService.getSlotById(bookingDtoForAgent.getSlotId());
    		if(slot != null) {
    			vehicleDto.setVehicleType(slot.getSlotType());
    		}
    	
    	vehicleService.createVehicleByDto(vehicleDto);
    	
    	BookingDto bookingDto = new BookingDto();
    	bookingDto.setAgentId(bookingDtoForAgent.getAgentId());
    	bookingDto.setSlotId(bookingDtoForAgent.getSlotId());
    	bookingDto.setCheckInTime(bookingDtoForAgent.getCheckInTime());
    	bookingDto.setCheckOutTime(bookingDtoForAgent.getCheckOutTime());
    	bookingDto.setVehicleNumber(bookingDtoForAgent.getVehicleNumber());

    	//Making a booking entry for marked slot
        Booking createdBooking = bookingService.createBooking(bookingDto);
        Slots slotForStatusUpdate = slotsService.getSlotById(bookingDtoForAgent.getSlotId());
        if(createdBooking != null) {
        	
        	if(SlotStatus.valueOf(bookingDtoForAgent.getPreviousSlotStatus()).equals(SlotStatus.FILLED))
        		slotForStatusUpdate.setSlotStatus(SlotStatus.FILLED);
        	else
        		slotForStatusUpdate.setSlotStatus(SlotStatus.BOOKED);
        	
        	slotsService.updateSlot(bookingDtoForAgent.getSlotId(), slotForStatusUpdate);
        	return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        }
        else {
        	slotForStatusUpdate.setSlotStatus(SlotStatus.valueOf(bookingDtoForAgent.getPreviousSlotStatus()));
        	slotsService.updateSlot(bookingDtoForAgent.getSlotId(), slotForStatusUpdate);
        	return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("bookingId") Integer bookingId, @Valid @RequestBody BookingDto bookingDto) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, bookingDto);
        if (updatedBooking != null) {
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("bookingId") int bookingId) {
    	//Here change status of slot.
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
