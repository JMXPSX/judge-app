package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.service.TacService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class TacController {
	
	@Autowired
	private TacService tacService;
	
	@PutMapping(value = "/tac/{userId}")
	public ResponseEntity<?> agreeTac (@PathVariable long userId){
		return new ResponseEntity<>(tacService.agreeTac(userId), HttpStatus.OK);		
	}
	

}
