package com.jsp.HomeServo.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dao.ServiceCostDao;
import com.jsp.HomeServo.dao.VendorDao;
import com.jsp.HomeServo.dao.WorkDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.ServiceCost;
import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCostException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByWorkException;
import com.jsp.HomeServo.util.ResponseStructure;

@Service
public class ServiceCostService {
	@Autowired
	ServiceCostDao dao;

	@Autowired
	VendorDao vendorDao;

	@Autowired
	WorkDao workDao;

	@Autowired
	CustomerDao customerDao;

	ServiceCost cost;

	//savecost
	public ResponseEntity<ResponseStructure<ServiceCost>> saveServiceCost(int v_id, int w_id) {
		Vendor vendor = vendorDao.getVendorById(v_id);
		if (vendor != null) {
			Work work = workDao.getWorkById(w_id);
			if (work != null) {
				double costPerDay = vendor.getCostPerDay();
				Date start = work.getStartDate();
				Date end = work.getEndDate();
				Duration duration = Duration.between(start.toLocalDate().atStartOfDay(),
						end.toLocalDate().atStartOfDay());
				int days = (int) duration.toDays();
				cost.setDays(days);
				cost.setTotalAmount(days * costPerDay);
				ServiceCost cost2 = dao.saveServiceCost(cost);
				work.setCost(cost2);
				List<ServiceCost> list = new ArrayList<>();
				list.add(cost2);
				list.addAll(vendor.getCosts());
				vendor.setCosts(list);
				vendorDao.updateVendor(vendor);
				workDao.updateWork(work);
				ResponseStructure<ServiceCost> structure = new ResponseStructure<>();
				structure.setData(cost2);
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Cost set Successfully");

				return new ResponseEntity<ResponseStructure<ServiceCost>>(structure, HttpStatus.CREATED);
			} else 
				throw new NoSuchElementFoundByWorkException("No Work found");
		} else 
			throw new NoSuchElementFoundByVendorException("Vendor not found");
	}
	
	//payment
	public ResponseEntity<ResponseStructure<ServiceCost>> payment(int c_id,ServiceCost cost){
		Customer customer=customerDao.getCustomerById(c_id);
		if(customer!=null) {
				ServiceCost cost2=dao.getServiceCostById(cost.getId());
				if(cost2!=null) {
					ResponseStructure<ServiceCost> structure=new ResponseStructure<>();
					structure.setData(dao.payServiceCost(cost));
					structure.setStatus(HttpStatus.FOUND.value());
					structure.setMessage("Payment Success");
					
					return new ResponseEntity<ResponseStructure<ServiceCost>>(structure,HttpStatus.FOUND);
				}else
					throw new NoSuchElementFoundByCostException("Payment Decline");
		}else
			throw new NoSuchElementFoundByCustomerException("Customer Not Found");
	}
}
