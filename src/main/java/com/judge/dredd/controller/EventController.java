package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.judge.dredd.dto.EventDetailDTO;
import com.judge.dredd.service.impl.EventDetailServiceImpl;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class EventController {
	
	@Autowired
	EventDetailServiceImpl eventDetailServiceImpl;

	@PostMapping(value = "/events")
	public ResponseEntity<?> addEvent (@RequestBody EventDetailDTO eventDetailDTO){
		return new ResponseEntity<>(eventDetailServiceImpl.save(eventDetailDTO), HttpStatus.OK);
	}
	
	@PutMapping(value = "/events")
	public ResponseEntity<?> updateEvent (@RequestBody EventDTO eventDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@GetMapping(value = "/events")
	public ResponseEntity<?> getAllEvents (){
		return new ResponseEntity<>(eventDetailServiceImpl.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/events/{eventId}")
	public ResponseEntity<?> getEvent (@PathVariable int eventId){
		return new ResponseEntity<>(eventDetailServiceImpl.getOne(eventId), HttpStatus.OK);
	}
	
	
	
	
	
	
}
