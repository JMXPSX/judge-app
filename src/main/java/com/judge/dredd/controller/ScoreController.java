package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.ScoreDTO;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/score/api"})
public class ScoreController {

	@RequestMapping(value = "/addorupdatescore/{entryId}", method = RequestMethod.POST)
	public ResponseEntity<?> addorUpdateScore (@PathVariable int entryId, @RequestBody ScoreDTO ScoreDTO){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/finalizescore/{entryId}", method = RequestMethod.POST)
	public ResponseEntity<?> addorUpdateScore (@PathVariable int entryId){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getScoreSummner/{username},{entryId}", method = RequestMethod.GET)
	public ResponseEntity<?> getScoreSummary (@PathVariable String username, @PathVariable int entryId){
		return new ResponseEntity<>("return pojo", HttpStatus.OK);
	}
}
