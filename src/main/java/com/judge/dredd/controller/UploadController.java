package com.judge.dredd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	@PostMapping(value = "dredd/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<>("done", HttpStatus.OK);
	}

}
