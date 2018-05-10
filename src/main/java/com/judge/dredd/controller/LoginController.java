package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.SystemUserService;

@RestController
public class LoginController {
	
	@Autowired
	SystemUserService systemUserService;

	@GetMapping("/dredd/api/login/{username},{password}")
	public ResponseEntity<?> login (@PathVariable String username, @PathVariable String password){
		return new ResponseEntity<>(systemUserService.login(username, password), HttpStatus.OK);
	}
}
