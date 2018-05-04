package com.judge.dredd.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.UserService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class LoginController {
	
	@Inject
	UserService userService;

	@RequestMapping(value = "/login/{username},{password}", method = RequestMethod.GET)
	public ResponseEntity<?> login (@PathVariable String username, @PathVariable String password){
		return new ResponseEntity<>(userService.login(username, password), HttpStatus.OK);
	}
}
