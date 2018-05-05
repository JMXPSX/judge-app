package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.EventDTO;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class EventController {

	@PostMapping(value = "/events")
	public ResponseEntity<?> addEvent (@RequestBody EventDTO eventDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@PutMapping(value = "/events")
	public ResponseEntity<?> aupdateEvent (@RequestBody EventDTO eventDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@GetMapping(value = "/events")
	public ResponseEntity<?> getAllEvents (){
		return new ResponseEntity<>("return list pojo", HttpStatus.OK);
	}
	
	@GetMapping(value = "/events/{eventId}")
	public ResponseEntity<?> getEvent (@PathVariable int eventId){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	
	
	
	
	
}
