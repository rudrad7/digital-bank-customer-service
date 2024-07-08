package com.db.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.db.commonUtil.CustomerValidation;
import com.db.dto.CustomerDto;
import com.db.entity.Customer;
import com.db.exception.CustomerException;
import com.db.repository.CustomerRepository;
import com.db.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ResponseEntity<Customer> onboardingCustomer(CustomerDto customerDto) throws CustomerException {
		logger.info("Enter into onboaringCustomer");
		Customer savedCustomer = new Customer();
		if (Objects.nonNull(customerDto)) {
			CustomerValidation.customerValid(customerDto);
			Customer customer = CustomerValidation.convertDtoToEntity(customerDto);
			//check phone number should be unique
			if(Objects.nonNull(customerRepository.findByPhone(customer.getPhone()))) {
				throw new CustomerException("Mobile Number is alreday in used !");
			}
			savedCustomer = customerRepository.save(customer);
		} else {
			logger.error("Customer details is null");
			throw new CustomerException("Customer details is null");
		}
		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> updateCustomerDetails(CustomerDto customerDto, String customerId)
			throws CustomerException {
		logger.info("Enter into updateCustomerDetails");
		if (Objects.nonNull(customerDto)) {
			CustomerValidation.customerValid(customerDto);
			Customer customerDetails = customerRepository.findByCustomerId(Integer.valueOf(customerId));
			customerDetails.setFirstName(customerDto.getFirstName());
			customerDetails.setLastName(customerDto.getLastName());
			customerDetails.setEmail(customerDto.getEmail());
			if(Objects.nonNull(customerRepository.findByPhone(customerDto.getPhone()))) {
				throw new CustomerException("Mobile Number is alreday in used !");
			}
			customerDetails.setPhone(String.valueOf(customerDto.getPhone()));
			customerDetails.setPassword(customerDto.getPassword());
			customerDetails.setAddress(customerDto.getAddress());
			customerRepository.save(customerDetails);
		} else {
			logger.error("Customer details is null");
			throw new CustomerException("Customer details is null");
		}
		return null;
	}

	@Override
	public ResponseEntity<CustomerDto> getCustomer(String customerId) throws CustomerException {
//		Customer customerDetails = customerRepository.findByCustomerId(customerId);
		Customer customerDetails = new Customer();
		CustomerDto customerDto = CustomerValidation.convertEntityToDto(customerDetails);
		return ResponseEntity.ok(customerDto);
	}

	@Override
	public ResponseEntity<String> deleteById(String customerId) {
		customerRepository.deleteById(Integer.valueOf(customerId));
		return ResponseEntity.ok("Customer Deleted Successfully !");
	}

	@Override
	public ResponseEntity<List<Customer>> getAllCustomerDetails() {
		return ResponseEntity.ok(customerRepository.findAll());
	}

}
