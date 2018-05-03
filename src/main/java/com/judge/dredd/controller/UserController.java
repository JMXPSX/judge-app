package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.UserDTO;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/user/api"})
public class UserController {

	@RequestMapping(value = "/addorupdateuser", method = RequestMethod.POST)
	public ResponseEntity<?> addorUpdateUser (@RequestBody UserDTO userDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getallusers", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers (){
		return new ResponseEntity<>("all users!", HttpStatus.OK);
	}
}
