package com.parking.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.LockSlotDto;
import com.parking.dto.SlotAvailabilityDto;
import com.parking.entities.SlotStatus;
import com.parking.entities.Slots;
import com.parking.services.SlotsService;

@RestController
@RequestMapping("/slot")
@CrossOrigin(origins = "http://localhost:3000")
public class SlotsController {

	@Autowired
	private SlotsService slotsService;

	@PostMapping("/add")
	public ResponseEntity<?> addSlot(@RequestBody Slots slot) {
		return new ResponseEntity<>(slotsService.addSlot(slot), HttpStatus.CREATED);
	}

	@GetMapping("/{slotId}")
	public ResponseEntity<?> getSlotById(@PathVariable String slotId) {
		return new ResponseEntity<>(slotsService.getSlotById(slotId), HttpStatus.OK);
	}

	@PostMapping("/list/updateSlot/{slotId}")
	public ResponseEntity<?> updateSlot(@PathVariable String slotId,@RequestBody Slots slot) {
		return new ResponseEntity<>(slotsService.updateSlot(slotId,slot), HttpStatus.CREATED);
	}

	@GetMapping("/deleteSlot/{slotId}")
	public ResponseEntity<?> deleteSlot(@PathVariable String slotId) {
		slotsService.deleteSlot(slotId);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<?> getAllSlots() {
		return new ResponseEntity<>(slotsService.getAllSlots(), HttpStatus.OK);
	}
	@PostMapping("/slotavailable")
	public ResponseEntity<?> isSlotAvailable(@Valid @RequestBody SlotAvailabilityDto slotAvailabilityDto){
		List<Slots> availableSlots = slotsService.isSlotAvailableBySlotType(slotAvailabilityDto.getCheckInTime(), slotAvailabilityDto.getCheckOutTime(), slotAvailabilityDto.getSlotType());
		return new ResponseEntity<>(availableSlots, HttpStatus.OK);
	}
	
	@GetMapping("/lockslot")
	public ResponseEntity<SlotStatus> lockSlot(@Valid @RequestBody LockSlotDto lockSlotDto){
		SlotStatus previousSlotStatus = slotsService.lockSlot(lockSlotDto);
		if(previousSlotStatus != null) {
			return new ResponseEntity<>(previousSlotStatus, HttpStatus.LOCKED);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
