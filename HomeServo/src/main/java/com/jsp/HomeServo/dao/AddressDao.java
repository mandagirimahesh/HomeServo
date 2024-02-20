package com.jsp.HomeServo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServo.dto.Address;
import com.jsp.HomeServo.repository.AddressRepo;

@Repository
public class AddressDao {
	
	@Autowired
	AddressRepo repo;
	
	//update
	public Address updateAddress(Address address) {
		if(repo.findById(address.getId()).isPresent()) {
			Address existAddress=repo.findById(address.getId()).get();
			if(address.getD_no()=="") {
				address.setD_no(existAddress.getD_no());
			}
			if(address.getStreet()=="") {
				address.setStreet(existAddress.getStreet());
			}
			if(address.getLandmark()=="") {
				address.setLandmark(existAddress.getLandmark());
			}
			if(address.getDistrict()=="") {
				address.setDistrict(existAddress.getDistrict());
			}
			if(address.getState()=="") {
				address.setState(existAddress.getState());
			}
			if(address.getPincode()==0) {
				address.setPincode(existAddress.getPincode());
			}
			
			return repo.save(address);
		}else {
			return null;
		}
	}
	
	//get by id
	public Address getAddById(int id) {
		if(repo.findById(id).isPresent()) {
			Address address = repo.findById(id).get();
			return address;
		}else {
			return null;
		}
	}

}
