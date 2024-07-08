package com.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DigitalBankCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankCustomerServiceApplication.class, args);
		System.out.println("Customer-service started on : 8081 ");
	}

}
