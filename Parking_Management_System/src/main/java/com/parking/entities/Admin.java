package com.parking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ADMIN")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class Admin {
    @Id
    @Column(length = 50)
    @Email(message = "Login is not valid")
    private String loginId;

    @NotBlank(message = "Password field cannot be blank")
    @Column(length = 50, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    
    @Size(min=10, max = 10)
	@Pattern(regexp = "^[1-9]\\d{9}$")
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank(message = "First name cannot be blank")
	@Column(length = 20, nullable = false)
	@Length(min = 4, max = 20, message="Number of characters in name field should be between 3 to 20")
	private String firstName;

    @NotBlank(message = "Last name cannot be blank")
	@Column(length = 20, nullable = false)
	@Length(min = 4, max = 20, message="Number of characters in name field should be between 3 to 20")
    private String lastName;

	public Admin(@Email(message = "Login is not valid") String loginId,
			@NotBlank(message = "Password field cannot be blank") String password,
			@Size(min = 10, max = 10) @Pattern(regexp = "^[1-9]\\d{9}$") String phoneNumber,
			@NotBlank(message = "First name cannot be blank") @Length(min = 4, max = 20, message = "Number of characters in name field should be between 3 to 20") String firstName,
			@NotBlank(message = "Last name cannot be blank") @Length(min = 4, max = 20, message = "Number of characters in name field should be between 3 to 20") String lastName) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Admin() {
		super();
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

    
}
