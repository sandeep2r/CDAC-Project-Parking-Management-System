package com.parking.dto;


import java.time.LocalDateTime;

import com.parking.entities.SlotType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SlotAvailabilityDto {
	private SlotType slotType;
	private LocalDateTime checkInTime;
	private LocalDateTime checkOutTime;
	
	
	public SlotAvailabilityDto(SlotType slotType, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
		super();
		this.slotType = slotType;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}
	public SlotAvailabilityDto() {
		super();
	}
	public SlotType getSlotType() {
		return slotType;
	}
	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
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
	
	
}