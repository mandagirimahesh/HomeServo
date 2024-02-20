package com.jsp.HomeServo.exception;

import lombok.Data;

@Data
public class EmailNotFoundForCustomer extends RuntimeException{
	
	private String messege="Invalid Email";

	public EmailNotFoundForCustomer(String messege) {
		super();
		this.messege = messege;
	}
	
	public EmailNotFoundForCustomer() {
		super();
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	
}
