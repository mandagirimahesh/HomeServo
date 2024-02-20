package com.jsp.HomeServo.exception;

public class PwdNotFoundForVendor extends RuntimeException{
	private String messege="Enter a valid password...";

	public PwdNotFoundForVendor(String messege) {
		super();
		this.messege = messege;
	}
	public PwdNotFoundForVendor() {
		super();
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	
}
