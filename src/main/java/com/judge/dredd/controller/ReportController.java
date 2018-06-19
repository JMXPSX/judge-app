package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.ReportService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping(value = "/report/{eventId}")
	public ResponseEntity<?> getReport(@PathVariable long eventId){
		return new ResponseEntity<>(reportService.getReport(eventId), HttpStatus.OK);
	}

}
