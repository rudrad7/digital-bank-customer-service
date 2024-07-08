package com.db.exception;

import java.time.LocalDateTime;

public class CustomerException extends RuntimeException{
	 private static final long serialVersionUID = 1L;

	 private LocalDateTime localDateTime;
	 private String details;
	private String message;

	public CustomerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message, String details, LocalDateTime localDateTime) {
		this.details = details;
		this.message = message;
		this.localDateTime = localDateTime;
	}
	

}
