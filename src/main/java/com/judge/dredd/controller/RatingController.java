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

	@PostMapping(value = "/rate")
	public ResponseEntity<?> addRating (@PathVariable RateDTO rateDTO){
		return new ResponseEntity<>(ratingService.save(rateDTO), HttpStatus.OK);
	}
	
	@PutMapping(value = "/rate")
	public ResponseEntity<?> updateRating (@RequestBody RateDTO rateDTO){
		return new ResponseEntity<>(ratingService.update(rateDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/rate/event/{eventId}/category/{categoryId}/entry/{entryId}")
	public ResponseEntity<?> getRating (@PathVariable long eventId, @PathVariable long categoryId, @PathVariable long entryId){
		return new ResponseEntity<>(ratingService.getRating(eventId, categoryId, entryId), HttpStatus.OK);
	}
}
