package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.service.CriteriaService;

@RestController
public class CriteriaController {
	
//	@Autowired
//	private CriteriaService criteriaService;
//	
//	@PostMapping("/dredd/api/criteria")
//	public ResponseEntity<?> addorUpdateCriteria (@RequestBody CriteriaDTO criteriaDTO){
//		System.out.println("here");
//		return new ResponseEntity<>(criteriaService.save(criteriaDTO), HttpStatus.OK);
//	}
//	
//	@GetMapping("/dredd/api/criteria/{id}")
//	public ResponseEntity<?> getCriteria (@PathVariable long id){
//		return new ResponseEntity<>(criteriaService.getOne(id), HttpStatus.OK);
//	}
//	
//	@GetMapping("/dredd/api/criteria")
//	public ResponseEntity<?> getAllCriteria (){
//		return new ResponseEntity<>(criteriaService.getAll(), HttpStatus.OK);
//	}
//	
//	@GetMapping("/dredd/api/criteriabyevent/{eventId}")
//	public ResponseEntity<?> getCriteriaByEventId (@PathVariable long eventId){
//		return new ResponseEntity<>(criteriaService.getByEventDetailId(eventId), HttpStatus.OK);
//	}
}
