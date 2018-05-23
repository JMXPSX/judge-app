package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.service.CommentsService;

@RestController
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
		
	
	@GetMapping("/dredd/api/comments/{entryId}/{judgeId}")
	public ResponseEntity<?> getComments (@PathVariable long entryId, @PathVariable long judgeId){
		return new ResponseEntity<>(commentsService.findByEntryIdAndJudgeId(entryId, judgeId), HttpStatus.OK);
	}
	
	@PutMapping(value = "/dredd/api/comments")
	public ResponseEntity<?> updateComments (@RequestBody CommentsDTO commentsDTO){
		return new ResponseEntity<>(commentsService.updateComments(commentsDTO), HttpStatus.OK);
	}
}
