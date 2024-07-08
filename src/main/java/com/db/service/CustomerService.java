package com.db.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.db.dto.CustomerDto;
import com.db.entity.Customer;


public interface CustomerService {
	
	public ResponseEntity<Customer> onboardingCustomer(CustomerDto customerDto);
	public ResponseEntity<String> updateCustomerDetails(CustomerDto customerDto, String customerId);
	public ResponseEntity<CustomerDto> getCustomer(String customerId);
	public ResponseEntity<String> deleteById(String customerId);
	public ResponseEntity<List<Customer>> getAllCustomerDetails();

}
