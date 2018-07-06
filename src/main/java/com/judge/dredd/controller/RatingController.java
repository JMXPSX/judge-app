package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.RateDTO;
import com.judge.dredd.service.RatingService;

@RestController
@RequestMapping({"/dredd/api"})
public class RatingController {
	
	@Autowired
	private RatingService ratingService;

	@PutMapping(value = "/rate")
	public ResponseEntity<?> setRating (@RequestBody RateDTO rateDTO){
		try {
			return new ResponseEntity<>(ratingService.update(rateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/rate/event/{eventId}/entry/{entryId}/judge/{appuserId}")
	public ResponseEntity<?> getRating (@PathVariable long eventId, @PathVariable long entryId, @PathVariable long appuserId){
		return new ResponseEntity<>(ratingService.getRating(eventId, entryId, appuserId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/rate/event/{eventId}/judge/{appuserId}")
	public ResponseEntity<?> getRating (@PathVariable long eventId, @PathVariable long appuserId){
		return new ResponseEntity<>(ratingService.getRating(eventId, appuserId), HttpStatus.OK);
	}
}
