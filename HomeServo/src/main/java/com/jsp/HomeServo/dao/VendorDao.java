package com.jsp.HomeServo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.repository.VendorRepo;

@Repository
public class VendorDao {
	@Autowired
	VendorRepo repo;

	// save
	public Vendor saveVendor(Vendor vendor) {
		return repo.save(vendor);
	}

	// update
	public Vendor updateVendor(Vendor vendor) {
		if (repo.findById(vendor.getId()).isPresent()) {
			Vendor existVendor = repo.findById(vendor.getId()).get();

			if (vendor.getName() == "") {
				vendor.setName(existVendor.getName());
			}
			if (vendor.getEmail() == "") {
				vendor.setEmail(existVendor.getEmail());
			}
			if (vendor.getPhone() == 0) {
				vendor.setPhone(existVendor.getPhone());
			}
			if (vendor.getPwd() == "") {
				vendor.setPwd(existVendor.getPwd());
			}
			if (vendor.getCostPerDay() == 0) {
				vendor.setCostPerDay(existVendor.getCostPerDay());
			}
			if (vendor.getAddress() == null) {
				vendor.setAddress(existVendor.getAddress());
			}
			if (vendor.getRole() == "") {
				vendor.setRole(existVendor.getRole());
			}
			if (vendor.getCosts() == null) {
				vendor.setCosts(existVendor.getCosts());
			}

			return repo.save(vendor);
		}
		return null;

	}

	// delete
	public Vendor deleteVendor(int id) {
		if (repo.findById(id).isPresent()) {
			Vendor vendor = repo.findById(id).get();
			repo.delete(vendor);
			return vendor;
		} else {
			return null;
		}
	}

	// get by id
	public Vendor getVendorById(int id) {
		if (repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		} else {
			return null;
		}
	}

	// get by email
	public Vendor getVendorByEmail(String email) {
		return repo.findByEmail(email);
	}

	// get all
	public List<Vendor> getAllVendors() {
		return repo.findAll();
	}

}
