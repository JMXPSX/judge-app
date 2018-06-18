package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.judge.dredd.service.FileService;

@RestController
public class FileController {
	
	@Autowired
	FileService fileService;	

	@PostMapping(value = "dredd/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<>(fileService.upload(file), HttpStatus.OK);
	}

}
