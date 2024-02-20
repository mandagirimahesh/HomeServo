package com.jsp.HomeServo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.service.CustomerService;
import com.jsp.HomeServo.util.ResponseStructure;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}
	
	@GetMapping("/customer/id")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@RequestParam int id) {
		return service.getCustomerById(id);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> login(@RequestParam String email,@RequestParam String pwd) {
		return service.login(email, pwd);
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@RequestParam int id) {
		return service.deleteCustomer(id);
	}
}
