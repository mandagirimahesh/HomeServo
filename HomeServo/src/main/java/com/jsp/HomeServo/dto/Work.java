package com.jsp.HomeServo.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;


@Entity
@Data
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private Date startDate;
	private Date endDate;
	private String description;
	
	

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToMany
	private List<Vendor> vendor;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	@OneToOne
	private ServiceCost cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Vendor> getVendor() {
		return vendor;
	}

	public void setVendor(List<Vendor> vendor) {
		this.vendor = vendor;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ServiceCost getCost() {
		return cost;
	}

	public void setCost(ServiceCost cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
