package com.db.commonUtil;

import org.apache.logging.log4j.util.Strings;

import com.db.dto.CustomerDto;
import com.db.entity.Customer;
import com.db.exception.CustomerException;

public class CustomerValidation {
	
	/**
	 * @implNote This method is created for validate customer details
	 * @param customerDto
	 * @throws CustomerException
	 */
	
	public static void customerValid(CustomerDto customerDto) throws CustomerException{
		if(Strings.isEmpty(customerDto.getFirstName())) {
			throw new CustomerException("First name should not be null");
		}
		if(Strings.isEmpty(customerDto.getLastName())) {
			throw new CustomerException("Last name should not be null");
		}
		if(Strings.isEmpty(customerDto.getEmail())) {
			throw new CustomerException("Email id should not be null");
		}
		if(Strings.isEmpty(customerDto.getAddress())) {
			throw new CustomerException("Address should not be null");
		}
		if(Strings.isEmpty(customerDto.getPassword())) {
			throw new CustomerException("Password should not be null");
		}
	}

	public static Customer convertDtoToEntity(CustomerDto customerDto) throws CustomerException{
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setPhone(String.valueOf(customerDto.getPhone()));
		customer.setPassword(customerDto.getPassword());
		customer.setEmail(customerDto.getEmail());
		customer.setAddress(customerDto.getAddress());
		return customer;
	}
	
	public static CustomerDto convertEntityToDto(Customer customer) throws CustomerException{
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPhone(customer.getPhone());
		customerDto.setPassword(customer.getPassword());
		return customerDto;
	}

}
