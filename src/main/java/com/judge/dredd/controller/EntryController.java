package com.judge.dredd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.service.EntryService;

@RestController
public class EntryController {

	@Autowired
	private EntryService entryService;
	
	@GetMapping(value = "dredd/api/entry/{eventId}/category/{categoryId}/users/{appUserId}")
	public ResponseEntity<?> getentry (@PathVariable long eventId, @PathVariable long categoryId, @PathVariable long appUserId){
		return new ResponseEntity<>(entryService.getEntriesByEventIdAndCategoryIdAndUserId(eventId, categoryId, appUserId), HttpStatus.OK);
	}
	
	@GetMapping(value = "dredd/api/entry/{eventId}/users/{appUserId}")
	public ResponseEntity<?> getentry (@PathVariable long eventId, @PathVariable long appUserId){
		return new ResponseEntity<>(entryService.getEntriesByEventIdAndCategoryIdAndUserId(eventId, appUserId), HttpStatus.OK);
	}
	
	@PostMapping(value = "dredd/api/entry")
	public ResponseEntity<?> addEntry (@RequestBody EntryDTO entryDTO){
		return new ResponseEntity<>(entryService.addEntryWithMembers(entryDTO), HttpStatus.OK);
	}
	
	@PostMapping(value = "/entries/{entryId}/judges")
	public ResponseEntity<?> addEntryJudges(@RequestBody List<Long> judges,@PathVariable long entryId){
		return new ResponseEntity<>(entryService.assignJudges(entryId, judges), HttpStatus.OK);
	}
	
//	
//	@GetMapping(value = "/entries/{entryId}")
//	public ResponseEntity<?> getEntry (@PathVariable int entryId){
//		return new ResponseEntity<>(entryService.getOne(entryId), HttpStatus.OK);
//	}
//	
//	@GetMapping(value = "/entries/event/{eventId}/{judgeId")
//	public ResponseEntity<?> getAllEntriesByEventIdAndJudgeId (int eventId, int judgeId){
//		//TODO
//		return new ResponseEntity<>("Not yet Implemented", HttpStatus.OK);
//	}
//	
//	@GetMapping(value = "/entries")
//	public ResponseEntity<?> getAllEntries (){
//		return new ResponseEntity<>(entryService.getAll(), HttpStatus.OK);
//	}
}
