package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.EventDTO;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/entry/api"})
public class EntryController {

	@RequestMapping(value = "/addorupdateentry", method = RequestMethod.POST)
	public ResponseEntity<?> addorUpdateEntry (@RequestBody EventDTO eventDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getentry/{entryId}", method = RequestMethod.GET)
	public ResponseEntity<?> getEntry (@PathVariable int entryId){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getallentires/{eventId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllEntriesByEventId (int eventId){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getallentires", method = RequestMethod.GET)
	public ResponseEntity<?> getAllEntriesd (){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
}
