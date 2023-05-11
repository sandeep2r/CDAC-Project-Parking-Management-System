package com.parking.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SLOTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Slots {

    @Id
    @NotBlank(message = "Slot ID cannot be blank")
    @Size(max = 10, message = "Slot ID cannot exceed 10 characters")
    private String slotId;

    //@NotBlank(message = "Slot type cannot be blank")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SlotType slotType;

    //@NotBlank(message = "Slot status cannot be blank")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SlotStatus slotStatus;

    @JsonIgnore(value = true)
    @OneToMany(mappedBy = "slot", cascade = CascadeType.PERSIST)
    private List<Booking> bookings;
    
    @PreRemove
    private void preRemove() {
    	bookings.forEach( booking -> booking.setSlot(null));
    }

	public Slots(
			@NotBlank(message = "Slot ID cannot be blank") @Size(max = 10, message = "Slot ID cannot exceed 10 characters") String slotId,
			SlotType slotType, SlotStatus slotStatus, List<Booking> bookings) {
		super();
		this.slotId = slotId;
		this.slotType = slotType;
		this.slotStatus = slotStatus;
		this.bookings = bookings;
	}

	public Slots() {
		super();
	}

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	public SlotStatus getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(SlotStatus slotStatus) {
		this.slotStatus = slotStatus;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

    
}	