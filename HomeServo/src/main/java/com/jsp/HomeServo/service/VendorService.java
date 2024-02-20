package com.jsp.HomeServo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dao.VendorDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.exception.EmailNotFoundForVender;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServo.exception.PwdNotFoundForVendor;
import com.jsp.HomeServo.util.ResponseStructure;

@Service
public class VendorService {
	@Autowired
	VendorDao dao;
	
	@Autowired
	CustomerDao cdao;

	// Save Vendor
	public ResponseEntity<ResponseStructure<Vendor>> saveVendor(Vendor vendor) {
		ResponseStructure<Vendor> structure = new ResponseStructure<>();
		structure.setData(dao.saveVendor(vendor));
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Vendor Saved Successfully");

		return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.CREATED);
	}

	// update Vendor
	public ResponseEntity<ResponseStructure<Vendor>> updateVendor(Vendor vendor) {
		Vendor vendor2 = dao.updateVendor(vendor);
		if (vendor2 != null) {
			ResponseStructure<Vendor> structure = new ResponseStructure<>();
			structure.setData(dao.updateVendor(vendor));
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage(vendor2.getName() + " Updated Successfully");

			return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.CREATED);
		} else {
			throw new NoSuchElementFoundByVendorException("id="+vendor.getId()+" Vendor not found");
		}
	}

	// Login Vendor...
	public ResponseEntity<ResponseStructure<Vendor>> login(String email, String pwd) {
		Vendor vendor = dao.getVendorByEmail(email);
		if (vendor != null) {
			if (vendor.getPwd().equals(pwd)) {
				ResponseStructure<Vendor> structure = new ResponseStructure<>();
				structure.setData(vendor);
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage(vendor.getName() + " login successfully as a Vendor");

				return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.FOUND);
			} else {
				throw new PwdNotFoundForVendor("Enter valid Password....");
			}
		} else {
			throw new EmailNotFoundForVender("Enter valid Email.....");
		}

	}

	// delete vendor...
	public ResponseEntity<ResponseStructure<Vendor>> deleteVendor(int id) {
		Vendor vendor = dao.deleteVendor(id);
		if(vendor != null) {
			ResponseStructure<Vendor> structure=new ResponseStructure<>();
			structure.setData(vendor);
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage(vendor.getName()+" Deleted");
			
			return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.OK);
		} else {
			throw new NoSuchElementFoundByVendorException("id=" + id + " Vendor not found");
		}

	}

	// Get Vendor by Id...
	public ResponseEntity<ResponseStructure<Vendor>> getVendorById(int id) {
		Vendor vendor = dao.getVendorById(id);
		if (vendor != null) {
			ResponseStructure<Vendor> structure = new ResponseStructure<>();
			structure.setData(vendor);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Id : " + id + " Found the vendor");

			return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.FOUND);

		} else {
			throw new NoSuchElementFoundByVendorException("id="+id+" Vendor not found");
		}

	}

	// get all vendors...
	public ResponseEntity<ResponseStructure<List<Vendor>>> getAllVendors(int c_id) {
		Customer customer = cdao.getCustomerById(c_id);
		if(customer != null) {
			List<Vendor> list = dao.getAllVendors();
			ResponseStructure<List<Vendor>> structure=new ResponseStructure<>();
			structure.setData(list);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Vendors found");
			
			return new ResponseEntity<ResponseStructure<List<Vendor>>> (structure, HttpStatus.FOUND);
		}else {
			throw new NoSuchElementFoundByCustomerException("Customer not found");
		}
	}

}
