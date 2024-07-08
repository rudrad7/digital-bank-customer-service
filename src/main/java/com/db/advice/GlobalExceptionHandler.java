package com.db.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.db.entity.Customer;
import com.db.exception.CustomerException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException customerException){
		return new ResponseEntity<String>("Exceptoin in account",HttpStatus.BAD_REQUEST);
	}

}
