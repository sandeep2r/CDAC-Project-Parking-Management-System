package com.parking.dto;


import java.time.LocalDateTime;

import com.parking.entities.BookingStatus;
import com.parking.entities.IsCheckedIn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDto {
	private Integer id;
	private Integer agentId;
	private String vehicleNumber;
	private Integer paymentId;
	private String slotId;
	private BookingStatus status;
	private LocalDateTime checkInTime;
	private LocalDateTime checkOutTime;
	private IsCheckedIn isCheckedIn;
	
	
	public BookingDto(Integer id, Integer agentId, String vehicleNumber, Integer paymentId, String slotId,
			BookingStatus status, LocalDateTime checkInTime, LocalDateTime checkOutTime, IsCheckedIn isCheckedIn) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.vehicleNumber = vehicleNumber;
		this.paymentId = paymentId;
		this.slotId = slotId;
		this.status = status;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.isCheckedIn = isCheckedIn;
	}
	public BookingDto() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
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