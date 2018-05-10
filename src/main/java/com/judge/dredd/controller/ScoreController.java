package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.service.ScoreService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class ScoreController {

	@Autowired
	private ScoreService scoreService;
	
	@PostMapping(value = "/scores")
	public ResponseEntity<?> addScore (@PathVariable int entryId, @RequestBody ScoreDTO scoreDTO){
		return new ResponseEntity<>(scoreService.save(scoreDTO), HttpStatus.OK);
	}
	
	@PutMapping(value = "/scores")
	public ResponseEntity<?> updateScore (@RequestBody ScoreDTO scoreDTO){
		return new ResponseEntity<>(scoreService.updateScore(scoreDTO), HttpStatus.OK);
	}
	
	@PostMapping(value = "/scores/done/{eventId}/{entryId}/{judgeId}")
	public ResponseEntity<?> setDone (@PathVariable long eventId, @PathVariable long entryId, @PathVariable long judgeId){
		return new ResponseEntity<>(scoreService.setDone(eventId, entryId, judgeId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/scores/{scoreId}")
	public ResponseEntity<?> getOne (@PathVariable int scoreId){
		return new ResponseEntity<>(scoreService.getOne(scoreId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/scores")
	public ResponseEntity<?> getScores (){
		return new ResponseEntity<>(scoreService.getAll(), HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/scores/finalize/{eventid}/{judgeId}")
	public ResponseEntity<?> finalizeScore (@PathVariable long eventid, @PathVariable long judgeId){
		return new ResponseEntity<>(scoreService.finalizeScore(eventid, judgeId), HttpStatus.OK);
	}
	
}
