package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;
		
	
	@GetMapping("/dredd/api/mainevent/{id}/{judgeId}")
	public ResponseEntity<?> getDisplayData (@PathVariable long eventId, @PathVariable long judgeId){
		return new ResponseEntity<>(eventService.getAllForDisplay(eventId, judgeId), HttpStatus.OK);
	}
	
}
