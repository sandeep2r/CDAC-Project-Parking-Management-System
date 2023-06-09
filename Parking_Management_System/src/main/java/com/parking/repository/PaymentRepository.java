package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parking.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}