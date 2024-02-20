package com.jsp.HomeServo.dao;

import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.repository.CustomerRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;

	@Autowired
	WorkDao workDao;

	// save
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}

	// update...
	public Customer updateCustomer(Customer customer) {
		if (repo.findById(customer.getId()).isPresent()) {
			Customer existCustomer = repo.findById(customer.getId()).get();

			if (customer.getName() == "") {
				customer.setName(existCustomer.getName());
			}
			if (customer.getEmail() == "") {
				customer.setEmail(existCustomer.getEmail());
			}
			if (customer.getPhone() == 0) {
				customer.setPhone(existCustomer.getPhone());
			}
			if (customer.getPwd() == "") {
				customer.setPwd(existCustomer.getPwd());
			}
			if (customer.getFamilyCount() == 0) {
				customer.setFamilyCount(existCustomer.getFamilyCount());
			}
			if (customer.getAddress() == null) {
				customer.setAddress(existCustomer.getAddress());
			}
			if (customer.getWorks() == null) {
				customer.setWorks(existCustomer.getWorks());
			}

			return repo.save(customer);
		}
		return null;
	}

	// get Customer by id
	public Customer getCustomerById(int id) {
		if (repo.findById(id).isPresent()) {
			Customer customer = repo.findById(id).get();
			return customer;
		} else {
			return null;
		}
	}

	// delete customer by id

	public Customer deleteCustomer(int id) {
		if (repo.findById(id).isPresent()) {
			Customer customer = repo.findById(id).get();
			List<Work> list = workDao.listOfWorks();
			if (list != null) {
				for (Work work : list) {
					Customer customer2=work.getCustomer();
					if(customer2!=null&&customer2.getId()==id) {
						work.setCustomer(null);
						workDao.updateWork(work);
					}
				}
			}
			repo.deleteById(id);
			return customer;
		}
		return null;
	}

	// get customer by email
	public Customer getCustomerByEmail(String email) {
		return repo.findByEmail(email);
	}
}
