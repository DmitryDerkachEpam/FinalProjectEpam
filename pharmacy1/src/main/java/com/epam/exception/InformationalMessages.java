package com.epam.exception;

public enum InformationalMessages {

	REGISTARTION_SUCCESSFUL ("Successfull registration! Please, login");
	
	private String message;
	
	private InformationalMessages (String message) {
		this.message = message;
	}
	
	public String getMessage () {
		return this.message;
	}
}
