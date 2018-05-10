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
public class EventDetailController {
	
	@Autowired
	EventDetailServiceImpl eventDetailServiceImpl;

	@PostMapping(value = "/eventdetail")
	public ResponseEntity<?> addEvent (@RequestBody EventDetailDTO eventDetailDTO){
		return new ResponseEntity<>(eventDetailServiceImpl.save(eventDetailDTO), HttpStatus.OK);
	}
	
	@PutMapping(value = "/eventdetail")
	public ResponseEntity<?> updateEvent (@RequestBody EventDetailDTO eventDetailDTO){
		return new ResponseEntity<>("Not yet Implemented", HttpStatus.OK);
	}
	
	@GetMapping(value = "/eventdetail")
	public ResponseEntity<?> getAllEvents (){
		return new ResponseEntity<>(eventDetailServiceImpl.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/eventdetail/{eventId}")
	public ResponseEntity<?> getEvent (@PathVariable int eventId){
		return new ResponseEntity<>(eventDetailServiceImpl.getOne(eventId), HttpStatus.OK);
	}
	
	
	
	
	
	
}
