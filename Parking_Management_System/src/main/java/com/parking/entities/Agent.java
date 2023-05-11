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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AGENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString(exclude = "password")
public class Agent extends BaseEntity{
	
    @Column(length = 50,nullable = false, unique = true)
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password field cannot be blank")
    @Column(length = 50, nullable = false)
    //@JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "First name cannot be blank")
	@Column(length = 20, nullable = false)
	@Length(min = 4, max = 20, message="Number of characters in name field should be between 3 to 20")
	private String firstName;

    @NotBlank(message = "Last name cannot be blank")
	@Column(length = 20, nullable = false)
	@Length(min = 4, max = 20, message="Number of characters in name field should be between 3 to 20")
    private String lastName;

    @NotBlank(message = "Address cannot be blank")
    @Column(length = 255, nullable = false)
    private String address;

    @Size(min=10, max = 10)
	@Pattern(regexp = "^[1-9]\\d{9}$")
    @Column(nullable = false)
    private String mobileNumber;

    @NotNull(message = "Logged In status cannot be null, it should either be true or false")
    @Column(nullable = false)
    private Boolean loggedIn;

    @JsonIgnore(value = true)
    @OneToMany(mappedBy = "agent", cascade = CascadeType.PERSIST)
    private List<Booking> bookings;
    
    @PreRemove
    private void preRemove() {
    	bookings.forEach( booking -> booking.setAgent(null));
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Agent() {
		super();
	}
    
}