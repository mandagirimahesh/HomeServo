package com.jsp.HomeServo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.exception.EmailNotFoundForCustomer;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.PwdNotFoundForCustomer;
import com.jsp.HomeServo.util.ResponseStructure;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao dao;

	// save
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		structure.setData(dao.saveCustomer(customer));
		structure.setMessage("Customer data saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
	}

	// update customer by id
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
		Customer customer2 = dao.updateCustomer(customer);
		if (customer2 != null) {
			ResponseStructure<Customer> structure = new ResponseStructure<>();
			structure.setData(dao.updateCustomer(customer));
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage(customer2.getName() + " Updated Successfully");

			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
		} else {
			throw new NoSuchElementFoundByCustomerException("id=" + customer.getId() + " Customer not found");
		}
	}

	// login
	public ResponseEntity<ResponseStructure<Customer>> login(String email, String pwd) {
		Customer customer = dao.getCustomerByEmail(email);
		if (customer != null) {

			if (customer.getPwd().equals(pwd)) {
				ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
				structure.setData(customer);
				structure.setMessage("Customer login Successfully");
				structure.setStatus(HttpStatus.FOUND.value());

				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);
			} else {
				throw new PwdNotFoundForCustomer("Check Your Password.....");
			}
		} else {
			throw new EmailNotFoundForCustomer("Invalid Email.....");
		}
	}

	// get customer by id
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id) {
		Customer customer = dao.getCustomerById(id);

		if (customer != null) {
			ResponseStructure<Customer> structure = new ResponseStructure<>();
			structure.setData(customer);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Successfully customer found");

			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementFoundByCustomerException(id + " is not Found");
		}

	}

	// delete customer by id
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int id) {
		Customer customer = dao.deleteCustomer(id);
		if (customer != null) {
			ResponseStructure<Customer> structure = new ResponseStructure<>();
			structure.setData(customer);
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Customer Deleted Successfully");

			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
		} else {
			throw new NoSuchElementFoundByCustomerException("with id=" + id + " Customer not found");
		}

	}
}
