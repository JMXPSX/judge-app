package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.NotesDTO;
import com.judge.dredd.service.NotesService;

@RestController
public class NotesController {

	@Autowired
	private NotesService notesService;
		
	
	@GetMapping("/dredd/api/notes/{entryId}/{judgeId}")
	public ResponseEntity<?> getNotes (@PathVariable long entryId, @PathVariable long judgeId){
		return new ResponseEntity<>(notesService.findByEntryIdAndJudgeId(entryId, judgeId), HttpStatus.OK);
	}
	
	@PutMapping(value = "/dredd/api/notes")
	public ResponseEntity<?> updateNotes (@RequestBody NotesDTO notesDTO){
		return new ResponseEntity<>(notesService.updateNotes(notesDTO), HttpStatus.OK);
	}
}
