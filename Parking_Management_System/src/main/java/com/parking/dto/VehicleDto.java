package com.parking.dto;

import com.parking.entities.SlotType;

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
public class VehicleDto {
	private String vehicleNumber;
	private SlotType vehicleType;
	private Integer customer;
	private String modelName;
	
	
	public VehicleDto(String vehicleNumber, SlotType vehicleType, Integer customer, String modelName) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.customer = customer;
		this.modelName = modelName;
	}
	public VehicleDto() {
		super();
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public SlotType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(SlotType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Integer getCustomer() {
		return customer;
	}
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	
}
