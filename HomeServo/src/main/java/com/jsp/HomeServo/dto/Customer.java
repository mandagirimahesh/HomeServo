package com.jsp.HomeServo.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private long phone;
	private String pwd;
	private int familyCount;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Work> works;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getFamilyCount() {
		return familyCount;
	}

	public void setFamilyCount(int familyCount) {
		this.familyCount = familyCount;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Work> getWorks() {
		return works;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}
	
	

	
	
}
