package com.jsp.HomeServo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServo.dto.ServiceCost;
import com.jsp.HomeServo.repository.ServiceCostRepo;

@Repository
public class ServiceCostDao {
	@Autowired
	ServiceCostRepo repo;
	
	public ServiceCost saveServiceCost(ServiceCost serviceCost) {
		return repo.save(serviceCost);
	}
	
	public ServiceCost payServiceCost(ServiceCost cost) {
		ServiceCost cost2=repo.findById(cost.getId()).get();
		if(cost2 != null) {
			cost2.setMode(cost.getMode());
			return repo.save(cost2);
		}else
			return null;
	}
	
	public ServiceCost getServiceCostById(int id) {
		ServiceCost cost=repo.findById(id).get();
		if(cost!=null) {
			return cost;
		}else {
			return null;
		}
	}

}
