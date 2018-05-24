package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/dredd/api/category/{eventId}")
	public ResponseEntity<?> getCategoriesByEvent (@PathVariable long eventId){
		return new ResponseEntity<>(categoryService.getCategoriesByEvent(eventId), HttpStatus.OK);
	}
}
