package com.parking.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.AuthRequest;
import com.parking.entities.Admin;
import com.parking.entities.Agent;
import com.parking.entities.BookingStatus;
import com.parking.entities.SlotStatus;
import com.parking.repository.AdminRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Admin getAdminById(String loginId) {
		return adminRepository.findById(loginId).orElse(null);
	}

	@Override
	public Admin updateAdmin(String loginId, Admin admin) {
		Admin existingAdmin = adminRepository.findById(loginId).orElse(null);
		if (existingAdmin != null) {
			existingAdmin.setFirstName(admin.getFirstName());
			existingAdmin.setLastName(admin.getLastName());
			existingAdmin.setPassword(admin.getPassword());
			existingAdmin.setPhoneNumber(admin.getPhoneNumber());
			return adminRepository.save(existingAdmin);
		} else {
			return null;
		}
	}

	@Override
	public void deleteAdmin(String loginId) {
		adminRepository.deleteById(loginId);

	}

	@Override
	public Admin login(AuthRequest authRequest) {

		return adminRepository.loginValidation(authRequest.getLoginId(), authRequest.getPassword());
	}

	@Override
	public Integer getTodaysCollections() {
		return adminRepository.getTodaysCollections(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIDNIGHT), BookingStatus.CANCELLED);
	}

	@Override
	public Integer getTodaysBookedSlots() {
		return adminRepository.getTodaysBookedSlots(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIDNIGHT), BookingStatus.CANCELLED);
	}

	@Override
	public Agent getCurrentlyWorkingAgent() {
		return adminRepository.getCurrentlyWorkingAgent(true);
	}

	@Override
	public Integer getNoOfVehiclesParked() {
		Integer i = adminRepository.getNoOfVehiclesParked(SlotStatus.FILLED);
		System.out.println("\n\n\n\n\n" + i + "\n\n\n\n\n\n");
		return i;
	}
}