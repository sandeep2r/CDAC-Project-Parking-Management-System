package com.parking.services;

import java.util.List;

import com.parking.dto.AuthRequest;
import com.parking.entities.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    Customer addCustomer(Customer customer);
    Customer updateCustomer(int id, Customer customer);
    void deleteCustomer(int id);
    Customer login(AuthRequest authRequest);
}