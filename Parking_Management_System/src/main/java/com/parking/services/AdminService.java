package com.parking.services;

import java.util.List;

import com.parking.dto.AuthRequest;
import com.parking.entities.Admin;
import com.parking.entities.Agent;

public interface AdminService {
	List<Admin> getAllAdmins();

	Admin getAdminById(String loginId);

	Admin addAdmin(Admin admin);

	Admin updateAdmin(String loginId, Admin admin);

	void deleteAdmin(String loginId);

	Admin login(AuthRequest authRequest);
	
	Integer getTodaysCollections();
	
	Integer getTodaysBookedSlots();
	
	Integer getNoOfVehiclesParked();
	
	Agent getCurrentlyWorkingAgent();

}
