package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.TacService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class TacController {
	
	@Autowired
	private TacService tacService;
	
	@PutMapping(value = "/tac")
	public ResponseEntity<?> updateTac (@RequestParam boolean tacFlag){
		return new ResponseEntity<>(tacService.updateTac(tacFlag), HttpStatus.OK);
		//return new ResponseEntity<>("not yet implemented", HttpStatus.OK);
	}
	

}
