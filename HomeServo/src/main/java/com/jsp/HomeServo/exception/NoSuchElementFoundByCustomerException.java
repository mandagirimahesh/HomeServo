package com.jsp.HomeServo.exception;

public class NoSuchElementFoundByCustomerException extends RuntimeException {
	private String messege = "Customer Not Found with the given id";

	public NoSuchElementFoundByCustomerException(String messege) {
		super();
		this.messege = messege;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	

}
