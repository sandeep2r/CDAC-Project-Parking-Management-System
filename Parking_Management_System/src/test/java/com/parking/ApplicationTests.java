package com.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.parking.entities.Customer;
import com.parking.repository.CustomerRepository;
import com.parking.services.CustomerService;

@SpringBootTest
class ApplicationTests {
	@Autowired
	
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Test
	void contextLoads() {
		List<Customer> allUsers = customerService.getAllCustomers();
		allUsers.forEach(System.out::println);
		assertEquals(2, allUsers.size());
	}

	
}




