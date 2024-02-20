package com.jsp.HomeServo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.service.WorkService;
import com.jsp.HomeServo.util.ResponseStructure;

@RestController
public class WorkController {
	@Autowired
	WorkService service;

	@PostMapping("/savework")
	public ResponseEntity<ResponseStructure<Work>> saveWork(@RequestBody Work work, @RequestParam int cus_id) {
		return service.saveWork(work, cus_id);
	}

	@PutMapping("/work/start")
	public ResponseEntity<ResponseStructure<Work>> startDate(@RequestParam int w_id, @RequestParam int v_id) {
		return service.startDate(w_id, v_id);
	}

	@PutMapping("/work/end")
	public ResponseEntity<ResponseStructure<Work>> endDate(@RequestParam int w_id, @RequestParam int v_id) {
		return service.endDate(w_id, v_id);
	}

	@GetMapping("/getwork")
	public ResponseEntity<ResponseStructure<Work>> getWorkById(@RequestParam int id) {
		return service.getWorkById(id);
	}

	//-------------------------------------------------------------------------
	@GetMapping("/getallworks")
	public ResponseEntity<ResponseStructure<List<Work>>> listOfWorks(@RequestParam int v_id) {
		return service.listOfWorks(v_id);
	}
	@GetMapping("/ongoingworks")
	public ResponseEntity<ResponseStructure<List<Work>>> onGoingWorks(@RequestParam int v_id) {
		return service.onGoingWorks(v_id);
	}
	@GetMapping("/completedworks")
	public ResponseEntity<ResponseStructure<List<Work>>> completedWorks() {
		return service.completedWorks();
	}
	//-------------------------------------------------------------------------
	@GetMapping("/findallworks")
	public ResponseEntity<ResponseStructure<List<Work>>> findAllWorks() {
		return service.findAllWorks();
	}
	
}
