package com.jsp.HomeServo.exception;

public class NoSuchElementFoundByVendorException extends RuntimeException{
		private String messege="Vendor not found with the given id";

	public NoSuchElementFoundByVendorException(String messege) {
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
