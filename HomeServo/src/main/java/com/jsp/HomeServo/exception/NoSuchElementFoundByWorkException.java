package com.jsp.HomeServo.exception;

public class NoSuchElementFoundByWorkException extends RuntimeException {

	private String messege="No Work found";

	public NoSuchElementFoundByWorkException(String messege) {
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
