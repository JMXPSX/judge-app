package com.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vote.service.VoteService;

@RestController
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@PostMapping("/vote/event/{eventId}/id/{participantId}/entry/{boothId}")
	public ResponseEntity<?> vote (@RequestParam long eventId, @RequestParam long participantId, @RequestParam long boothId){
		return new ResponseEntity<>(voteService.vote(eventId, participantId, boothId), HttpStatus.OK);	
	}
	
	@GetMapping("/vote/result/event/{eventId}")
	public ResponseEntity<?> getResults (@RequestParam long eventId){
		return new ResponseEntity<>(voteService.getResults(eventId), HttpStatus.OK);
	}
	

}
