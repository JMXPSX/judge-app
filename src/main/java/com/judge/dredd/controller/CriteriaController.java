package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.CriteriaDTO;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/criteria/api"})
public class CriteriaController {

	@RequestMapping(value = "/addorupdatecriteria", method = RequestMethod.POST)
	public ResponseEntity<?> addorUpdateCriteria (@RequestBody CriteriaDTO criteriaDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getcriteria/{eventId}", method = RequestMethod.GET)
	public ResponseEntity<?> getCriteria (@PathVariable int eventId){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getallcriteria", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCriteria (){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
}
