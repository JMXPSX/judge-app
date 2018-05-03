package com.judge.dredd.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class LoginController {
	
	@Inject
	LoginService loginService;

	@RequestMapping(value = "/login/{user},{pass}", method = RequestMethod.GET)
	public ResponseEntity<?> login (@PathVariable String user, @PathVariable String pass){
		return new ResponseEntity<>(loginService.login(user, pass), HttpStatus.OK);
	}
}
