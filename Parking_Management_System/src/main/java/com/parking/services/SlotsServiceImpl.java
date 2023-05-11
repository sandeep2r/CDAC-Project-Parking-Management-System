package com.parking.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.LockSlotDto;
import com.parking.entities.SlotStatus;
import com.parking.entities.SlotType;
import com.parking.entities.Slots;
import com.parking.entities.Vehicle;
import com.parking.repository.SlotsRepository;

@Service
@Transactional
public class SlotsServiceImpl implements SlotsService {

    @Autowired
    private SlotsRepository slotRepository;
    
    @Autowired
    private VehicleService vehicleService;

    @Override
    public List<Slots> getAllSlots() {
        return slotRepository.findAll();
    }

    @Override
    public Slots getSlotById(String slotId) {
        return slotRepository.findById(slotId).orElse(null);
    }

    @Override
    public Slots addSlot(Slots slot) {
        return slotRepository.save(slot);
    }

    @Override
    public Slots updateSlot(String slotId, Slots slotDetails) {
        Slots slot = slotRepository.findById(slotId).orElse(null);
        if(slot != null) {
        	slot.setSlotType(slotDetails.getSlotType());
            slot.setSlotStatus(slotDetails.getSlotStatus());
            return slotRepository.save(slot);
        }else {
        	return null;
        }
    }

    @Override
    public void deleteSlot(String slotId) {
        slotRepository.deleteById(slotId);
    }
    
    @Override
	public List<Slots> isSlotAvailableBySlotType(LocalDateTime checkInTime, LocalDateTime checkOutTime, SlotType slotType) {
		if(slotType == null)
			return null;
		return slotRepository.isSlotAvailable(checkInTime, checkOutTime, slotType);
	}

	@Override
	public List<Slots> isSlotAvailable(LocalDateTime checkInTime, LocalDateTime checkOutTime, String vehicleNumber) {
		Vehicle vehicle = vehicleService.getVehicleByNumber(vehicleNumber);
		if(vehicle == null)
			return null;
		//SlotType slotType = SlotType.valueOf(vehicle.getVehicleType().getVehicleType());
		SlotType slotType = vehicle.getVehicleType().getVehicleType();
		if(slotType == null)
			return null;
		return slotRepository.isSlotAvailable(checkInTime, checkOutTime, slotType);
	}

	@Override
	public Slots availableSlotMarkedForBooking(LocalDateTime checkInTime, LocalDateTime checkOutTime, String vehicleNumber) {
		List<Slots> availableSlots = isSlotAvailable(checkInTime, checkOutTime, vehicleNumber);
		if(availableSlots == null || availableSlots.size() <= 0)
			return null;
		else {
			
			//Method-1
			//Slots selectedSlot = availableSlots.get(0);
			//selectedSlot.setSlotStatus(SlotStatus.BOOKED);
			//return slotRepository.save(selectedSlot);
			Integer noOfupdatedSlots;
			Slots slotIdAndPreviousStatusObject = new Slots();
			//Method-2 //This method is much more synchronized
			for(Slots selectedSlot : availableSlots) {
				slotIdAndPreviousStatusObject.setSlotStatus(selectedSlot.getSlotStatus());
				noOfupdatedSlots = slotRepository.updateSlotStatus(selectedSlot.getSlotId(), SlotStatus.IN_PROCESS);
				if(noOfupdatedSlots != null && noOfupdatedSlots > 0) {
					//selectedSlot.setSlotStatus(SlotStatus.BOOKED); //To be used when complete selectedSlot object was returned from the method.
					slotIdAndPreviousStatusObject.setSlotId(selectedSlot.getSlotId());
					return slotIdAndPreviousStatusObject;
				}
			}
			return null;
		}
	}

	@Override
	public SlotStatus lockSlot(LockSlotDto lockSlotDto) {
		Slots slot = slotRepository.findById(lockSlotDto.getSlotId()).orElse(null);
		SlotStatus previousSlotStatus = null;
		if(slot != null) {
			List<Slots> availableSlots = slotRepository.isSlotAvailable(lockSlotDto.getCheckInTime(), lockSlotDto.getCheckOutTime(), slot.getSlotType());
			for(Slots selectedSlot : availableSlots) {
				if(selectedSlot.getSlotId().equals(lockSlotDto.getSlotId())) {
					previousSlotStatus =  selectedSlot.getSlotStatus();
					Integer updatedRowsInDb = slotRepository.updateSlotStatus(lockSlotDto.getSlotId(), SlotStatus.IN_PROCESS);
					if(updatedRowsInDb != null && updatedRowsInDb > 0) {
						return previousSlotStatus;
					}
					else {
						return null;
					}
				}
			}
			return null;
		}
		else
			return null;
	}
}