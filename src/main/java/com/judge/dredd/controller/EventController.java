package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.EventDTO;
import com.judge.dredd.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/dredd/api/events/{appUserId}")
	public ResponseEntity<?> getEventsByUser (@PathVariable long appUserId){
		return new ResponseEntity<>(eventService.getEventsByUser(appUserId), HttpStatus.OK);
	}
	
	@PostMapping("/dredd/api/events/")
	public ResponseEntity<?> createEvent (@RequestBody EventDTO eventDTO){
		return new ResponseEntity<>(eventService.addEvent(eventDTO), HttpStatus.OK);
	}
	
	
		
	
//	@GetMapping("/dredd/api/mainevent/{eventId}/{judgeId}")
//	public ResponseEntity<?> getDisplayData (@PathVariable long eventId, @PathVariable long judgeId){
//		return new ResponseEntity<>(eventService.getAllForDisplay(eventId, judgeId), HttpStatus.OK);
//	}
	
//	@GetMapping(value = "/dredd/api/mainevent/summary/{eventId}")
//	public ResponseEntity<?> getFinalizedScoreSummary (@PathVariable long eventId){
//		return new ResponseEntity<>(eventService.getFinalizedScoreSummary(eventId), HttpStatus.OK);
//	}
	
}
