package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.CategoryDTO;
import com.judge.dredd.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/dredd/api/category/{eventId}")
	public ResponseEntity<?> getCategoriesByEvent (@PathVariable long eventId){
		return new ResponseEntity<>(categoryService.getCategoriesByEvent(eventId), HttpStatus.OK);
	}
	
	@PostMapping("/dredd/api/category/")
	public ResponseEntity<?> createCategory (@RequestBody CategoryDTO categoryDTO){
		try{
			return new ResponseEntity<>(categoryService.addCategory(categoryDTO), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/dredd/api/category/user/{appUserId}")
	public ResponseEntity<?> getCategoriesByUser (@PathVariable long appUserId){
		return new ResponseEntity<>(categoryService.getCategoriesByUser(appUserId), HttpStatus.OK);
	}
	
	@GetMapping("/dredd/api/all/categories/")
	public ResponseEntity<?> getAllCategories(){
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}
}
