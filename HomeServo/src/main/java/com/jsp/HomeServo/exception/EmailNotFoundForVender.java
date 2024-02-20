package com.jsp.HomeServo.exception;

public class EmailNotFoundForVender extends RuntimeException{
	private String messege="Enter valid email";
	public EmailNotFoundForVender(String messege) {
		super();
		this.messege=messege;
	}
	public EmailNotFoundForVender() {
		super();
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	
}
