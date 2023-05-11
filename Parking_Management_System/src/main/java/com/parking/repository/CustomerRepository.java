package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
	@Query("select c from Customer c where c.email = :loginId and c.password = :password")
	Customer loginValidation(String loginId, String password);
}