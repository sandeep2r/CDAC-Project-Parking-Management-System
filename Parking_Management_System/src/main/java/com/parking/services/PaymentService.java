package com.parking.services;

import java.util.List;

import com.parking.entities.Payment;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(int id);
    Payment addPayment(Payment payment);
    Payment updatePayment(int id, Payment payment);
    void deletePayment(int id);
}