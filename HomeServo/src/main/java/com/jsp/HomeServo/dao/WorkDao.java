package com.jsp.HomeServo.dao;

import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.repository.WorkRepo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDao {

	@Autowired
	WorkRepo repo;

	// save
	public Work saveWork(Work work) {
		return repo.save(work);
	}

	// get work by id
	public Work getWorkById(int id) {
		Work work = repo.findById(id).get();
		if (work != null) {
			return work;
		}
		return null;
	}

	// update work
	public Work updateWork(Work work) {
		if (repo.findById(work.getId()).isPresent()) {
			Work existWork = repo.findById(work.getId()).get();

			if (work.getAddress() == null) {
				work.setAddress(existWork.getAddress());
			}
			if (work.getCost() == null) {
				work.setCost(existWork.getCost());
			}
			if (work.getCustomer() == null) {
				work.setCustomer(existWork.getCustomer());
			}
			if (work.getType() == null) {
				work.setType(existWork.getType());
			}
			if (work.getVendor() == null) {
				work.setVendor(existWork.getVendor());
			}
			if (work.getStartDate() == null) {
				work.setStartDate(existWork.getStartDate());
			}
			if (work.getEndDate() == null) {
				work.setEndDate(existWork.getEndDate());
			}

			return repo.save(work);
		} else {
			return null;
		}
	}

	// get all works
	public List<Work> listOfWorks() {
		List<Work> list = repo.listOfWorks();
		if (list != null) {
			return list;
		}
		return null;
	}

	// ongoing works
	public List<Work> onGoingWorks() {
		List<Work> list = repo.onGoingWorks();
		if (list != null) {
			return list;
		}
		return null;
	}

	// completed works
	public List<Work> completedWorks() {
		List<Work> list = repo.completedWorks();
		if (list != null) {
			return list;
		}
		return null;
	}

	// find all works
	public List<Work> findAllWorks() {
		List<Work> list = repo.findAll();
		if (list != null) {
			return list;
		}
		return null;
	}
	
}
