package com.parking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VEHICLE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @Column(length = 50)
    private String vehicleNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "vehicle_type", nullable = false) ---------// OLD
    @JoinColumn(name = "vehicle_type")
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "customer_id", nullable = false) ---------// OLD
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotBlank(message = "Model name cannot be blank")
    @Column(length = 50)
    private String modelName;

    @JsonIgnore(value = true)
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.PERSIST)
    private List<Booking> bookings;
    
    @PreRemove
    private void preRemove() {
    	bookings.forEach( booking -> booking.setVehicle(null));
    }

	public Vehicle(String vehicleNumber, VehicleType vehicleType, Customer customer,
			@NotBlank(message = "Model name cannot be blank") String modelName, List<Booking> bookings) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.customer = customer;
		this.modelName = modelName;
		this.bookings = bookings;
	}

	public Vehicle() {
		super();
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
   
}