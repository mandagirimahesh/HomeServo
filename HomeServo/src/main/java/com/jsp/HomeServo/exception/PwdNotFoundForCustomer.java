package com.jsp.HomeServo.exception;

public class PwdNotFoundForCustomer extends RuntimeException {

	String messege = "Check Your password";

	public PwdNotFoundForCustomer(String messege) {
		super();
		this.messege = messege;
	}

	public PwdNotFoundForCustomer() {
		super();
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

}
