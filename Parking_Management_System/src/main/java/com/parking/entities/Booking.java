package com.parking.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BOOKING")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking extends BaseEntity{
	
    //@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "vehicle_number", nullable = false)
    @JoinColumn(name = "vehicle_number")
    private Vehicle vehicle;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "payment_id", nullable = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "slot_id", nullable = false)
    @JoinColumn(name = "slot_id")
    private Slots slot;

    @NotNull(message = "Booking status cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @NotNull(message = "Check-in time cannot be null")
    @Column(nullable = false)
    private LocalDateTime checkInTime;

    @NotNull(message = "Check-out time cannot be null")
    @Column(nullable = false)
    private LocalDateTime checkOutTime;
    
    @NotNull(message = "Is Checked in cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsCheckedIn isCheckedIn;

	public Booking(Agent agent, Vehicle vehicle, Payment payment, Slots slot,
			@NotNull(message = "Booking status cannot be null") BookingStatus status,
			@NotNull(message = "Check-in time cannot be null") LocalDateTime checkInTime,
			@NotNull(message = "Check-out time cannot be null") LocalDateTime checkOutTime,
			@NotNull(message = "Is Checked in cannot be null") IsCheckedIn isCheckedIn) {
		super();
		this.agent = agent;
		this.vehicle = vehicle;
		this.payment = payment;
		this.slot = slot;
		this.status = status;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.isCheckedIn = isCheckedIn;
	}

	public Booking() {
		super();
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Slots getSlot() {
		return slot;
	}

	public void setSlot(Slots slot) {
		this.slot = slot;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public IsCheckedIn getIsCheckedIn() {
		return isCheckedIn;
	}

	public void setIsCheckedIn(IsCheckedIn isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

   
}