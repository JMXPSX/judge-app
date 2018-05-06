package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.service.SystemUserService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class UserController {
  
	@Autowired
	private SystemUserService systemUserService;
	
	@PostMapping(value = "/users")
	public ResponseEntity<?> addUser (@RequestBody UserDTO userDTO){
		return new ResponseEntity<>(systemUserService.save(userDTO), HttpStatus.OK);
	}
	
	@PutMapping(value = "/users")
	public ResponseEntity<?> updateUser (@RequestBody UserDTO userDTO){
		return new ResponseEntity<>(systemUserService.save(userDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<?> getAllUsers (){
		return new ResponseEntity<>("all users!", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/users/{userId}")
	public ResponseEntity<?> deleteUser (@PathVariable int userId){
		return new ResponseEntity<>("all users!", HttpStatus.OK);
	}
	
	@GetMapping(value = "/users/usertype/{userType}")
	public ResponseEntity<?> getAllUsersByType (@PathVariable int userType){
		return new ResponseEntity<>("all users!", HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/users/{username}")
	public ResponseEntity<?> getUsersByUsername (@PathVariable String username){
		return new ResponseEntity<>("all users!", HttpStatus.OK);
	}

}
