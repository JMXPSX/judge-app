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
import com.judge.dredd.dto.EventDTO;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/event/api"})
public class EventController {

	@RequestMapping(value = "/addorupdateevent", method = RequestMethod.POST)
	public ResponseEntity<?> addorUpdateEvent (@RequestBody EventDTO eventDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getallevents", method = RequestMethod.GET)
	public ResponseEntity<?> getAllEvents (){
		return new ResponseEntity<>("return list pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getevent/{eventId}", method = RequestMethod.GET)
	public ResponseEntity<?> getEvent (@PathVariable int eventId){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	
	
	
	
	
}
