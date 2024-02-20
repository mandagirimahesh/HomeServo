package com.jsp.HomeServo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServo.dao.AddressDao;
import com.jsp.HomeServo.dto.Address;
import com.jsp.HomeServo.exception.NoSuchElementFoundForAddressException;
import com.jsp.HomeServo.util.ResponseStructure;

@Service
public class AddressService {
	
	@Autowired
	AddressDao dao;
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
		Address address2=dao.updateAddress(address);
		if(address2 != null) {
			ResponseStructure<Address> structure=new ResponseStructure<>();
			structure.setData(dao.updateAddress(address));
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Address Updated Successfully");
			
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
		}else {
			throw new NoSuchElementFoundForAddressException("Address Not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Address>> getAddById(int id) {
		Address address=dao.getAddById(id);
		if(address != null) {
			ResponseStructure<Address> structure=new ResponseStructure<>();
			structure.setData(dao.getAddById(id));
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Address found");
			
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
		}else {
			throw new NoSuchElementFoundForAddressException("Address Not found");
		}
	}

}
