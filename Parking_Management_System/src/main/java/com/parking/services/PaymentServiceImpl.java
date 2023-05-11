package com.parking.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.entities.Payment;
import com.parking.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(int paymentId, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if(payment != null) {
        	payment.setPaymentType(paymentDetails.getPaymentType());
            payment.setTransactionId(paymentDetails.getTransactionId());
            payment.setTotalAmount(paymentDetails.getTotalAmount());
            payment.setPaymentStatus(paymentDetails.getPaymentStatus());
            return paymentRepository.save(payment);
        }else {
        	return null;
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}