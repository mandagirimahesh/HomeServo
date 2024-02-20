package com.jsp.HomeServo.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.HomeServo.util.ResponseStructure;

@ControllerAdvice
public class ExceptionHandleForHomeServo extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> SQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("Email already given change Email");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailNotFoundForCustomer.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFoundForCustomer(EmailNotFoundForCustomer em) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(em.getMessege());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("wrong email");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PwdNotFoundForCustomer.class)
	public ResponseEntity<ResponseStructure<String>> pwdNotFoundForCustomer(PwdNotFoundForCustomer ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(ex.messege);
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("wrong psw");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailNotFoundForVender.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFoundforVender(EmailNotFoundForVender ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("wrong email");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessege());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PwdNotFoundForVendor.class)
	public ResponseEntity<ResponseStructure<String>> exceptionHandler(PwdNotFoundForVendor ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("wrong psw");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessege());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementFoundByCustomerException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByCustomerException(
			NoSuchElementFoundByCustomerException ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessege());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Customer not found");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementFoundByVendorException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByVendorException(
			NoSuchElementFoundByVendorException ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessege());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Vendor not found");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementFoundForAddressException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundForAddressException(
			NoSuchElementFoundForAddressException ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessege());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Address not found");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementFoundByWorkException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByWorkException(
			NoSuchElementFoundByWorkException ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessege());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No Work found");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementFoundByCostException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByCostException(
			NoSuchElementFoundByCostException ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessege());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Payment UnSuccess");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
