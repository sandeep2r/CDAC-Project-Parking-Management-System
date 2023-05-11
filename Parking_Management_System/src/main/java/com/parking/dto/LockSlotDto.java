package com.parking.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LockSlotDto {
	
	public LockSlotDto() {
		super();
	}
	private String slotId;
	private LocalDateTime checkInTime;
	private LocalDateTime checkOutTime;
	
	public LockSlotDto(String slotId, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
		super();
		this.slotId = slotId;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
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
	
	
}
