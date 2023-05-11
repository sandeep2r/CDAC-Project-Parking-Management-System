package com.parking.services;

import java.time.LocalDateTime;
import java.util.List;

import com.parking.dto.LockSlotDto;
import com.parking.entities.SlotStatus;
import com.parking.entities.SlotType;
import com.parking.entities.Slots;


public interface SlotsService {
    List<Slots> getAllSlots();
    //List<Slots> getSlotsByTypeAndStatus(SlotType type, SlotStatus status);
    Slots getSlotById(String slotId);
    Slots addSlot(Slots slot);
    Slots updateSlot(String slotId, Slots slot);
    void deleteSlot(String slotId);
    List<Slots> isSlotAvailable(LocalDateTime checkInTime, LocalDateTime checkOutTime, String vehicleNumber);
    List<Slots> isSlotAvailableBySlotType(LocalDateTime checkInTime, LocalDateTime checkOutTime, SlotType slotType);
    Slots availableSlotMarkedForBooking(LocalDateTime checkInTime, LocalDateTime checkOutTime, String vehicleNumber);
    SlotStatus lockSlot(LockSlotDto lockSlotDto);
}