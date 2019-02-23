package com.revature.sjm.exception;

public class InvalidCustomerNameException extends IllegalArgumentException {

	private final String localMessage;
	private final String input;
	
	public InvalidCustomerNameException(String input, String localMessage) {
		this.input = input;
		this.localMessage = localMessage;
	}
	
	@Override
	public String toString() {
		return "InvalidCustomerException [localMessage=" + localMessage + ", input=" + input + "]";
	}
	
}
