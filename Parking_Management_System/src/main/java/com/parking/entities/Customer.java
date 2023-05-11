package com.parking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class Customer extends BaseEntity{
	
	@NotBlank(message = "First name cannot be blank")
	@Column(length = 20, nullable = false)
	@Length(min = 4, max = 20, message="Number of characters in name field should be between 3 to 20")
	private String firstName;

	@NotBlank(message = "last name cannot be blank")
	@Column(length = 20, nullable = false)
	@Length(min = 4, max = 20, message="Number of characters in name field should be between 3 to 20")
    private String lastName;

	@Column(length = 50, unique = true)
    @Email(message = "Email is not valid")
    private String email;

	@NotBlank(message = "Password field cannot be blank")
    @Column(length = 50, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

	@Size(min=10, max = 10)
	//@Pattern(regexp = "^[1-9]\\d{9}$")
	@Column(nullable = false)
    private String mobileNumber;

	//@NotBlank(message = "Address cannot be blank")
    @Column(length = 255)
    private String address;

    @JsonIgnore(value = true)
    @OneToMany(mappedBy = "vehicleType", cascade = CascadeType.PERSIST)
    private List<Vehicle> vehicles;
    
    @PreRemove
    private void preRemove() {
       vehicles.forEach( vehicle -> vehicle.setVehicleType(null));
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Customer(
			@NotBlank(message = "First name cannot be blank") @Length(min = 4, max = 20, message = "Number of characters in name field should be between 3 to 20") String firstName,
			@NotBlank(message = "last name cannot be blank") @Length(min = 4, max = 20, message = "Number of characters in name field should be between 3 to 20") String lastName,
			@Email(message = "Email is not valid") String email,
			@NotBlank(message = "Password field cannot be blank") String password,
			@Size(min = 10, max = 10) String mobileNumber, String address, List<Vehicle> vehicles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.vehicles = vehicles;
	}

	public Customer() {
		super();
	}
    
}