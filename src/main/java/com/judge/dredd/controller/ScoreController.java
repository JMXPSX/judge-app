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
	public ResponseEntity<?> addScore (@RequestBody ScoreDTO scoreDTO){
		return new ResponseEntity<>(scoreService.save(scoreDTO), HttpStatus.OK);
	}
	
	@PutMapping(value = "/scores")
	public ResponseEntity<?> updateScore (@RequestBody ScoreDTO scoreDTO){
		return new ResponseEntity<>(scoreService.updateScore(scoreDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/scores/all")
	public ResponseEntity<?> getScores (){
		return new ResponseEntity<>(scoreService.getAll(), HttpStatus.OK);
	}
	
//	@GetMapping(value = "/scores/events/{eventId}/categories/{categoryId}/entry/{entryId}")
//	public ResponseEntity<?> getScores (@PathVariable long eventId, @PathVariable long categoryId, @PathVariable long entryId){
//		return new ResponseEntity<>(scoreService.getScoreByEventIdAndCategoryIdAndEntryId(eventId, categoryId, entryId), HttpStatus.OK);
//	}
//	
//	@GetMapping(value = "/scores/events/{eventId}/categories/{categoryId}/entry/{entryId}/appuser/{appUserId")
//	public ResponseEntity<?> getScores (@PathVariable long eventId, @PathVariable long categoryId, @PathVariable long entryId, @PathVariable long appUserId){
//		return new ResponseEntity<>(scoreService.getScoreByEventIdAndCategoryIdAndEntryIdAndAppUserId(eventId, categoryId, entryId, appUserId), HttpStatus.OK);
//	}
	
	@GetMapping(value = "/scores/events/{eventId}/entry/{entryId}")
	public ResponseEntity<?> getScores (@PathVariable long eventId, @PathVariable long entryId){
		return new ResponseEntity<>(scoreService.getScoreByEventIdAndEntryId(eventId, entryId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/scores/events/{eventId}/entry/{entryId}/appuser/{appUserId}")
	public ResponseEntity<?> getScoreByJudge (@PathVariable long eventId, @PathVariable long entryId, @PathVariable long appUserId){
		return new ResponseEntity<>(scoreService.getScoreByEventIdAndEntryIdAndAppUserId(eventId, entryId, appUserId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/scores/events/{eventId}/appuser/{appUserId}")
	public ResponseEntity<?> getAllScoresByJudge (@PathVariable long eventId, @PathVariable long appUserId){
		return new ResponseEntity<>(scoreService.getScoresByEventIdAndAppUserId(eventId, appUserId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/entry/{entryId}/judge/{judgeId}")
	public ResponseEntity<?> doneEntries(@PathVariable long entryId,@PathVariable long judgeId){
		return new ResponseEntity<>(scoreService.doneEntries(entryId, judgeId), HttpStatus.OK);
	}
	
//	@PostMapping(value = "/scores/finalize/{eventid}/{judgeId}")
//	public ResponseEntity<?> finalizeScore (@PathVariable long eventid, @PathVariable long judgeId){
//		return new ResponseEntity<>(scoreService.finalizeScore(eventid, judgeId), HttpStatus.OK);
//	}
	
}
