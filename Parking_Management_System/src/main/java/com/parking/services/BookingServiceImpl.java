package com.parking.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.BookingDto;
import com.parking.entities.Agent;
import com.parking.entities.Booking;
import com.parking.entities.BookingStatus;
import com.parking.entities.IsCheckedIn;
import com.parking.entities.Payment;
import com.parking.entities.PaymentStatus;
import com.parking.entities.PaymentType;
import com.parking.entities.SlotStatus;
import com.parking.entities.Slots;
import com.parking.entities.Vehicle;
import com.parking.repository.BookingRepository;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private AgentService agentService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private SlotsService slotService;

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.getAllBookings();
	}

	@Override
	public Booking getBookingById(Integer bookingId) {
		Booking booking = bookingRepository.getBookingById(bookingId);
		if (booking != null)
			return booking;
		else
			return null;
	}

	@Override
	public List<BookingDto> getAllBookingDtos() {
		return bookingRepository.getAllBookingDtos();
	}

//	@Override
//	public BookingDto getBookingDtoById(Integer bookingId) {
//		BookingDto bookingDto = bookingRepository.getBookingDtosById(bookingId);
//		if(bookingDto != null)
//			return bookingDto;
//		else
//			return null;
//	}

	@Override
	public Booking createBooking(BookingDto bookingDto) {
		// Vehicle Object for given vehicle Number
		Vehicle vehicle = bookingDto.getVehicleNumber() != null
				? vehicleService.getVehicleByNumber(bookingDto.getVehicleNumber())
				: null;

		// Agent object for given agent id or null
		Agent agent = bookingDto.getAgentId() != null ? agentService.getAgentById(bookingDto.getAgentId()) : null;

		// Slot Object for given slot id
		Slots slot = slotService.getSlotById(bookingDto.getSlotId());

		// creating a payment object for this booking and setting its values
		Payment paymentToBeInserted = new Payment();
		paymentToBeInserted.setPaymentStatus(PaymentStatus.PENDING);
		paymentToBeInserted.setPaymentType(PaymentType.OFFLINE);
		// calculating TotalAmount
		Duration duration = Duration.between(bookingDto.getCheckInTime(), bookingDto.getCheckOutTime());
		long hours = duration.toMinutes() / 60;
		Integer totalAmount = (int) (vehicle.getVehicleType().getRate() * hours);
		paymentToBeInserted.setTotalAmount(totalAmount);
		// setTransactionId(null); // do not set it because it is by default null
		Payment payment = paymentService.addPayment(paymentToBeInserted);

		// new Booking object and adding foreign objects into it.
		Booking newBooking = new Booking();
		newBooking.setAgent(agent);
		newBooking.setPayment(payment);
		newBooking.setVehicle(vehicle);
		newBooking.setSlot(slot);
		newBooking.setCheckInTime(
				bookingDto.getCheckInTime() != null ? bookingDto.getCheckInTime() : LocalDateTime.now());
		newBooking.setCheckOutTime(bookingDto.getCheckOutTime());
		newBooking.setIsCheckedIn(
				bookingDto.getIsCheckedIn() != null ? bookingDto.getIsCheckedIn() : IsCheckedIn.NOT_CHECKED_IN);
		newBooking.setStatus(BookingStatus.CONFIRMED);

		// inserting new Booking into DB
		return bookingRepository.save(newBooking);

	}

	@Override
	public Booking updateBooking(Integer bookingId, BookingDto bookingDto) {

		Booking existingBooking = bookingRepository.getBookingById(bookingId);
		if (existingBooking != null) {

			Agent agent = bookingDto.getAgentId() != null ? agentService.getAgentById(bookingDto.getAgentId()) : null;
			Slots slot = bookingDto.getSlotId() != null ? slotService.getSlotById(bookingDto.getSlotId()) : null;
			Vehicle vehicle = bookingDto.getVehicleNumber() != null
					? vehicleService.getVehicleByNumber(bookingDto.getVehicleNumber())
					: null;
//			Payment payment = bookingDto.getPaymentId() != null ? paymentService.getPaymentById(bookingDto.getPaymentId()) : null;

			existingBooking.setAgent(agent);
			existingBooking.setSlot(slot);
			existingBooking.setVehicle(vehicle);
			existingBooking.setStatus(bookingDto.getStatus());
			existingBooking.setCheckInTime(bookingDto.getCheckInTime());
			existingBooking.setCheckOutTime(bookingDto.getCheckOutTime());
//			existingBooking.setPayment(payment);
			return bookingRepository.save(existingBooking);

		} else {
			return null;
		}

	}
	
	@Override
	public void deleteBooking(int bookingId) {
		Booking booking = bookingRepository.getBookingById(bookingId);
		Slots slot = booking.getSlot();
		booking.setStatus(BookingStatus.CANCELLED);
		slot.setSlotStatus(SlotStatus.EMPTY);		
		bookingRepository.deleteById(bookingId);
		
	}

}
