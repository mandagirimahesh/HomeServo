package com.jsp.HomeServo.exception;

public class NoSuchElementFoundForAddressException extends RuntimeException {
	private String messege="Address Not found";

	public NoSuchElementFoundForAddressException(String messege) {
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
