package com.parking.controllers;

import java.util.List;

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

import com.parking.dto.AuthRequest;
import com.parking.entities.Admin;
import com.parking.services.AdminService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody AuthRequest authRequest) {
        Admin admin = adminService.login(authRequest);
        if(admin != null) {
        	
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

	@PostMapping("/addAdmin")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
		Admin newAdmin = adminService.addAdmin(admin);
		return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		List<Admin> admins = adminService.getAllAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}

	@GetMapping("/{loginId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable String loginId) {
		Admin admin = adminService.getAdminById(loginId);
		if (admin != null) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/editAdmin/{loginId}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable String loginId, @RequestBody Admin admin) {
		Admin updatedAdmin = adminService.updateAdmin(loginId, admin);
		if (updatedAdmin != null) {
			return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/deleteAdmin/{loginId}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable String loginId) {
		adminService.deleteAdmin(loginId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
