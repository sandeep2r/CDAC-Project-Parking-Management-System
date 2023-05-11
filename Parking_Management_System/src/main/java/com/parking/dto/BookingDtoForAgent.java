package com.parking.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDtoForAgent {
	private Integer agentId;
	private String vehicleNumber;
	private String slotId;
	private LocalDateTime checkInTime;
	private LocalDateTime checkOutTime;
	private String previousSlotStatus;
	
	
	public BookingDtoForAgent(Integer agentId, String vehicleNumber, String slotId, LocalDateTime checkInTime,
			LocalDateTime checkOutTime, String previousSlotStatus) {
		super();
		this.agentId = agentId;
		this.vehicleNumber = vehicleNumber;
		this.slotId = slotId;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.previousSlotStatus = previousSlotStatus;
	}
	public BookingDtoForAgent() {
		super();
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
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
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
	public String getPreviousSlotStatus() {
		return previousSlotStatus;
	}
	public void setPreviousSlotStatus(String previousSlotStatus) {
		this.previousSlotStatus = previousSlotStatus;
	}
	
	
}