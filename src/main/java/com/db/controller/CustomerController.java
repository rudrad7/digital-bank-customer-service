package com.db.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.dto.CustomerDto;
import com.db.entity.Customer;
import com.db.exception.CustomerException;
import com.db.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@PostMapping("/save")
	public ResponseEntity<String> customerOnboarding(@RequestBody CustomerDto customerDto) throws CustomerException {
		logger.info("Enter into customerOnboarding " + customerDto.toString());
		try {
			ResponseEntity<Customer> customerResponseEntity = customerService.onboardingCustomer(customerDto);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(customerResponseEntity.getBody().getCustomerId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			logger.error("Exception while saving customer: ", e);
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@PostMapping("/update/{customerId}")
	public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomerDto customerDto,
			@PathVariable String customerId) {
		logger.info("Enter into updateCustomerDetails " + customerDto.toString() + "CustomerId is : " + customerId);
		// Validate customerId
		if (customerId == null || customerId.trim().isEmpty()) {
			logger.error("Invalid customerId: " + customerId);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer ID must not be null or empty");
		}
		try {
			customerService.updateCustomerDetails(customerDto, customerId);
			return ResponseEntity.ok("Customer saved successfully!");
		} catch (Exception e) {
			logger.error("Exception while updating customer: ", e);
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable String customerId) {
		if (customerId == null || customerId.trim().isEmpty()) {
			logger.error("Invalid customerId: " + customerId);
			throw new CustomerException();
		}
		return customerService.getCustomer(customerId);
	}

	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<String> deleteById(@PathVariable String customerId) {
		if (customerId == null || customerId.trim().isEmpty()) {
			logger.error("Invalid customerId: " + customerId);
			throw new CustomerException();
		}
		return customerService.deleteById(customerId);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllCustomerDetails() {
		return customerService.getAllCustomerDetails();
	}
}
