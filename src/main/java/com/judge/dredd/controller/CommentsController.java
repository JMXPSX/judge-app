package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.service.CommentsService;

@RestController
public class CommentsController {

	@Autowired
	private CommentsService commentsService;		
	
	@GetMapping("/dredd/api/comments/{entryId}")
	public ResponseEntity<?> getComments (@PathVariable long entryId){
		return new ResponseEntity<>(commentsService.findCommentsByEntryId(entryId), HttpStatus.OK);
	}
	
	@PutMapping("/dredd/api/comments")
	public void updateComments (@RequestBody CommentsDTO commentsDTO){
		commentsService.updateComments(commentsDTO);
	}
	
	@PostMapping(value = "/dredd/api/comments")
	public ResponseEntity<?> addComments (@RequestBody CommentsDTO commentsDTO){
		try {
			return new ResponseEntity<>(commentsService.addComment(commentsDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
