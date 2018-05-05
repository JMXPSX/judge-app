package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.UserDTO;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class UserController {

	@PostMapping(value = "/users")
	public ResponseEntity<?> addorUpdateUser (@RequestBody UserDTO userDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<?> getAllUsers (){
		return new ResponseEntity<>("all users!", HttpStatus.OK);
	}
	
	@GetMapping(value = "/users/{username}")
	public ResponseEntity<?> getUsersByUsername (@PathVariable String username){
		return new ResponseEntity<>("all users!", HttpStatus.OK);
	}
}
