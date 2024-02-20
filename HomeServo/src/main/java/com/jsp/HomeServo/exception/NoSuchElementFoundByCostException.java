package com.jsp.HomeServo.exception;

public class NoSuchElementFoundByCostException extends RuntimeException {
	private String messege = "Payment Unsuccess";

	public NoSuchElementFoundByCostException(String messege) {
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
