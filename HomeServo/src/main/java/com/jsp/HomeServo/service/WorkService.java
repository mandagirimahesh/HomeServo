package com.jsp.HomeServo.service;

import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dao.VendorDao;
import com.jsp.HomeServo.dao.WorkDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByWorkException;
import com.jsp.HomeServo.util.ResponseStructure;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

	@Autowired
	WorkDao dao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	VendorDao vendorDao;

	// save
	public ResponseEntity<ResponseStructure<Work>> saveWork(Work work, int cus_id) {
		Customer customer = customerDao.getCustomerById(cus_id);
		if (customer != null) {
			work.setCustomer(customer);
			ResponseStructure<Work> structure = new ResponseStructure<>();
			structure.setData(dao.saveWork(work));
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("customer work created successfully");

			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.CREATED);
		} else {
			throw new NoSuchElementFoundByCustomerException("with id : " + cus_id + " Customer not found");
		}
	}

	// start Date
	public ResponseEntity<ResponseStructure<Work>> startDate(int w_id, int v_id) {
		Vendor vendor = vendorDao.getVendorById(v_id);
		if (vendor != null) {
			Work work = dao.getWorkById(w_id);
			if (work != null) {
				Date date = new Date(new java.util.Date().getTime());
				work.setStartDate(date);
				List<Vendor> list = new ArrayList<>();
				list.add(vendor);
				work.setVendor(list);
				ResponseStructure<Work> structure = new ResponseStructure<>();
				structure.setData(dao.updateWork(work));
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Work saved Successfully");

				return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.CREATED);
			} else {
				throw new NoSuchElementFoundByWorkException("No Work found");
			}
		} else {
			throw new NoSuchElementFoundByVendorException("id=" + v_id + " Vendor not found");
		}
	}

	// end date
	public ResponseEntity<ResponseStructure<Work>> endDate(int w_id, int v_id) {
		Vendor vendor = vendorDao.getVendorById(v_id);
		if (vendor != null) {
			Work work = dao.getWorkById(w_id);
			if (work != null) {
				Date date = new Date(new java.util.Date().getTime());
				work.setEndDate(date);
				ResponseStructure<Work> structure = new ResponseStructure<>();
				structure.setData(dao.updateWork(work));
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("Work Updated Successfully");

				return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);
			} else
				throw new NoSuchElementFoundByWorkException("Work not found");
		} else
			throw new NoSuchElementFoundByVendorException("id=" + v_id + " Vendor not found");
	}

	// get work by id
	public ResponseEntity<ResponseStructure<Work>> getWorkById(int id) {
		Work work = dao.getWorkById(id);
		if (work != null) {
			ResponseStructure<Work> structure = new ResponseStructure<>();
			structure.setData(work);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Work Found");

			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);
		} else
			throw new NoSuchElementFoundByWorkException("Work Not found");
	}

	// list of works
	public ResponseEntity<ResponseStructure<List<Work>>> listOfWorks(int v_id) {
		Vendor vendor = vendorDao.getVendorById(v_id);
		if (vendor != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<>();
			structure.setData(dao.listOfWorks());
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found the works list");

			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);
		} else
			throw new NoSuchElementFoundByVendorException("id=" + v_id + " Vendor not found");

	}

	// onGoing works
	public ResponseEntity<ResponseStructure<List<Work>>> onGoingWorks(int v_id) {
		Vendor vendor = vendorDao.getVendorById(v_id);
		if (vendor != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<>();
			structure.setData(dao.onGoingWorks());
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found the works list");

			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);
		} else
			throw new NoSuchElementFoundByVendorException("id=" + v_id + " Vendor not found");

	}

	// completed works
	public ResponseEntity<ResponseStructure<List<Work>>> completedWorks() {
		ResponseStructure<List<Work>> structure = new ResponseStructure<>();
		structure.setData(dao.completedWorks());
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Found the works list");

		return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);

	}

	// findAllWorks
	public ResponseEntity<ResponseStructure<List<Work>>> findAllWorks() {

		ResponseStructure<List<Work>> structure = new ResponseStructure<>();
		structure.setData(dao.findAllWorks());
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Found the works list");

		return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);

	}
}
