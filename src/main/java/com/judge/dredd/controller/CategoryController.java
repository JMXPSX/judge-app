package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

	@GetMapping("/dredd/api/category/{eventId}")
	public ResponseEntity<?> getCategoriesByEvent (@PathVariable long eventId){
		return new ResponseEntity<>(eventService.getEventsByUser(appUserId), HttpStatus.OK);
	}
}
