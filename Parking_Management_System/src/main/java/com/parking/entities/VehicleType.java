package com.parking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VEHICLETYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleType {

    @Id
    @Column(nullable = false)
    //@Size(max = 50, message = "Vehicle type cannot exceed 50 characters") -- //This constraint was used when vehicleType property was of String type.
    @Enumerated(EnumType.STRING)
    private SlotType vehicleType;

    //@NotBlank(message = "Rate cannot be blank")
    @NotNull(message = "Rate cannot be null")
    @Column(nullable = false)
    @Positive(message = "Rate must be greater than 0")
    private Integer rate;
    
    @JsonIgnore(value = true)
    @OneToMany(mappedBy = "vehicleType", cascade = CascadeType.PERSIST)
    private List<Vehicle> vehicles;
    
    @PreRemove
    private void preRemove() {
       vehicles.forEach( vehicle -> vehicle.setVehicleType(null));
    }

	public VehicleType(SlotType vehicleType,
			@NotNull(message = "Rate cannot be null") @Positive(message = "Rate must be greater than 0") Integer rate,
			List<Vehicle> vehicles) {
		super();
		this.vehicleType = vehicleType;
		this.rate = rate;
		this.vehicles = vehicles;
	}

	public VehicleType() {
		super();
	}

	public SlotType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(SlotType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
    
}