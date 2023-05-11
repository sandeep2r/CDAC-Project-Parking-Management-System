package com.parking.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.AuthRequest;
import com.parking.entities.Customer;
import com.parking.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(int customerId, Customer customer) {
        Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
        if(existingCustomer != null) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPassword(customer.getPassword());
            existingCustomer.setMobileNumber(customer.getMobileNumber());
            existingCustomer.setAddress(customer.getAddress());
            return customerRepository.save(existingCustomer);
        }
        else
        {
        	return null;
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerRepository.deleteById(customerId);
    }

	@Override
	public Customer login(AuthRequest authRequest) {

		return customerRepository.loginValidation(authRequest.getLoginId(), authRequest.getPassword());
	}
}