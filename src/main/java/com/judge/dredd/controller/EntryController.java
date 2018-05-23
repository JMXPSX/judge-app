package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.service.EntryService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class EntryController {

	@Autowired
	private EntryService entryService;
	
	@PostMapping(value = "/entries")
	public ResponseEntity<?> addorUpdateEntry (@RequestBody EntryDTO entryDTO){
		return new ResponseEntity<>(entryService.save(entryDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/entries/{entryId}")
	public ResponseEntity<?> getEntry (@PathVariable int entryId){
		return new ResponseEntity<>(entryService.getOne(entryId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/entries/event/{eventId}/{judgeId")
	public ResponseEntity<?> getAllEntriesByEventIdAndJudgeId (int eventId, int judgeId){
		//TODO
		return new ResponseEntity<>("Not yet Implemented", HttpStatus.OK);
	}
	
	@GetMapping(value = "/entries")
	public ResponseEntity<?> getAllEntries (){
		return new ResponseEntity<>(entryService.getAll(), HttpStatus.OK);
	}
}
