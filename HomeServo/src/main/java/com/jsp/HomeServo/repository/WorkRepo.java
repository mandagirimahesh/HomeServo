package com.jsp.HomeServo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServo.dto.Work;

public interface WorkRepo extends JpaRepository<Work, Integer>{
	@Query("select a from Work a where a.startDate=null")
	public List<Work> listOfWorks();
	
	@Query("select a from Work a where a.startDate!=null and a.endDate=null")
	public List<Work> onGoingWorks();
	
	@Query("select a from Work a where a.startDate!=null and a.endDate!=null")
	public List<Work> completedWorks();
	
}
