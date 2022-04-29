package com.epam.exception;

public enum ErrorMessages {
	
	REGISTARTION_ERROR ("Unable to register! Please, check input data and try again"),
	LOGIN_ERROR ("Unable to login! Please, check input data and try again");
	
	private String message;
	
	private ErrorMessages (String message) {
		this.message = message;
	}
	
	public String getMessage () {
		return this.message;
	}

}
