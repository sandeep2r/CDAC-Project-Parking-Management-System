package com.parking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entities.SlotStatus;
import com.parking.entities.SlotType;
import com.parking.entities.Slots;

@Repository
public interface SlotsRepository extends JpaRepository<Slots, String> {
	@Query("SELECT e "
			+ " FROM Slots e "
			+ " WHERE NOT EXISTS ("
			+ "    SELECT c"
			+ "    FROM Slots c"
			+ "    WHERE EXISTS ("
			+ "        SELECT b"
			+ "        FROM Booking b"
			+ "        WHERE ?2 > b.checkInTime AND ?1 < b.checkOutTime AND b.status != 'CANCELLED' AND b.slot = c.slotId"
			+ "    ) AND c.slotId = e.slotId"
			+ " ) AND e.slotType = ?3"
			+ " ")
	List<Slots> isSlotAvailable(LocalDateTime checkInTime, LocalDateTime checkOutTime, SlotType slotType);
	
	@Modifying
	@Query("update Slots s set s.slotStatus = ?2 where s.slotId = ?1 and s.slotStatus != ?2")
	Integer updateSlotStatus(String slotId, SlotStatus SlotStatus);
	
}